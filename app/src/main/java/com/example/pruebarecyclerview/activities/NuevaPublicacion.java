package com.example.pruebarecyclerview.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebarecyclerview.R;
import com.example.pruebarecyclerview.models.Post;
import com.example.pruebarecyclerview.providers.AuthProvider;
import com.example.pruebarecyclerview.providers.ImageProvider;
import com.example.pruebarecyclerview.providers.PostProvider;
import com.example.pruebarecyclerview.providers.UserProvider;
import com.example.pruebarecyclerview.utils.FileUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import dmax.dialog.SpotsDialog;

public class NuevaPublicacion extends AppCompatActivity {

    //VARIABLES GLOBALES

    private final int GALLERY_REQUIEST_CODE=1;
    private final int PHOTO_REQUIEST_CODE=3;
    Button mButtonPost;
    EditText mInputDescription;
    Spinner mCategoria;

    ImageProvider mImageProvider;
    PostProvider mPostProvider;
    AuthProvider mAuthProvider;
    File mImageFile;
    AlertDialog mDialog;
    AlertDialog.Builder mBuilderSelector;
    CharSequence options[];

    String mCat="", mDescription="";
    ImageView mImageViewPost;

    String mAbsolutePhotoPath;
    String mPhotoPath;
    File mPhotoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_publicacion);

        initComponents();

        //Accion al hacer click en publicar
        mButtonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickPost();
            }
        });

        //Accion para abrir la galeria o la camara
        mImageViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectOptionImage(GALLERY_REQUIEST_CODE);

                //openGallery();
            }
        });
    }

    private void selectOptionImage(int requestCode) {
        mBuilderSelector.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    openGallery(requestCode);
                }else if(i==1){
                    takePhoto();
                }
            }
        });
        mBuilderSelector.show();
    }

    private void takePhoto() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(NuevaPublicacion.this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                goToCamera();
            }else{
                ActivityCompat.requestPermissions(NuevaPublicacion.this, new String[]{Manifest.permission.CAMERA}, PHOTO_REQUIEST_CODE);
            }
        }else {
            goToCamera();
        }
    }

    private void goToCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File photoFile=null;
            try {
                photoFile=createPhotoFile();
            }catch (Exception e){
                toastError("Error al abrir archivo");
            }
            if(photoFile!=null){
                Uri photoUri= FileProvider.getUriForFile(NuevaPublicacion.this,"com.example.pruebarecyclerview",photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(takePictureIntent,PHOTO_REQUIEST_CODE);
            }

    }

    private File createPhotoFile() throws IOException {
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photoFile = File.createTempFile(
          new Date()+"_photo",
                ".jpg",
                storageDir
        );
        mPhotoPath ="file: "+photoFile.getAbsolutePath();
        mAbsolutePhotoPath=photoFile.getAbsolutePath();
        return photoFile;
    }

    private void clickPost() {
        mDescription= mInputDescription.getText().toString();
        mCat =(String)mCategoria.getSelectedItem();
        if(!mDescription.isEmpty() && !mCat.isEmpty()){
            if (mImageFile!=null){
                saveImage();
            }else {
                toastError("Ingresa una imagen");
                //Toast.makeText(this, "Ingresa una imagen", Toast.LENGTH_SHORT).show();
            }
        }else {
            toastError("Por favor ingresa una descripcion");
            //Toast.makeText(this, "Por favor ingresa una descripcion", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImage() {
        mDialog.show();
        mImageProvider.save(NuevaPublicacion.this,mImageFile).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    mImageProvider.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url= uri.toString();
                            Post post = new Post();
                            post.setDescripcion(mDescription);
                            post.setCategory(mCat);
                            post.setImagen(url);
                            post.setIdUser(mAuthProvider.getUid());
                            mPostProvider.save(post).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> taskSave) {
                                    mDialog.dismiss();
                                    if(taskSave.isSuccessful()){
                                        Toast.makeText(NuevaPublicacion.this, "La publicacion se guardo", Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(NuevaPublicacion.this,InterfazPrinc.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }else {
                                        toastError("Error al crear publicacion");
                                    }
                                }
                            });
                        }
                    });
                    /*
                    AQUI deberia poner otra cosa que no sea una
                    Toast.makeText(NuevaPublicacion.this, "Imagen almacenada", Toast.LENGTH_SHORT).show();
                    */
                }else {
                    mDialog.dismiss();
                    toastError("Error al subir imagen");
                    //Toast.makeText(NuevaPublicacion.this, "Error al subir imagen", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openGallery(int requestCode) {
        Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(gallery,requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //SELECCION DE IMAGEN DESDE GALERIA
        if(requestCode==GALLERY_REQUIEST_CODE && resultCode==RESULT_OK){
            try {
                mImageFile = FileUtil.from(this,data.getData());
                mImageViewPost.setImageBitmap(BitmapFactory.decodeFile(mImageFile.getAbsolutePath()));
            }catch (Exception a){
                Toast.makeText(this, "Se produjo un error", Toast.LENGTH_SHORT).show();
            }

        }

        //SELECCION DE IMAGEN TOMANDO FOTO
        if(requestCode==PHOTO_REQUIEST_CODE && resultCode== RESULT_OK ){
            Picasso.with(NuevaPublicacion.this).load(mPhotoPath).into(mImageViewPost);
        }

    }
    public  void initComponents(){
        //Providers
        mImageProvider= new ImageProvider();
        mPostProvider= new PostProvider();
        mAuthProvider= new AuthProvider();

        mCategoria= findViewById(R.id.categoria);
        //Lista con las opciones de carreras
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.lista, android.R.layout.simple_list_item_1);
        mCategoria.setAdapter(adapter);

        mButtonPost= findViewById(R.id.publicarPost);
        mImageViewPost= findViewById(R.id.addImage);
        mInputDescription= findViewById(R.id.descripcion);
        mDialog = new  SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.UploadPost)
                .setCancelable(false)
                .build();
        mBuilderSelector= new AlertDialog.Builder(this);
        mBuilderSelector.setTitle("Seleccionar imagen");
        options= new CharSequence[]{"De galeria", "Tomar foto"};
    }
    public void toastError(String msm){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view =layoutInflater.inflate(R.layout.toast_error, (ViewGroup)findViewById(R.id.ll_custom_toast_error));
        TextView txtMessage= view.findViewById(R.id.message);
        txtMessage.setText(msm);

        Toast toast= new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.BOTTOM,0,200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}