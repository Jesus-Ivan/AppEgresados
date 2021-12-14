package com.example.pruebarecyclerview.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pruebarecyclerview.R;
import com.example.pruebarecyclerview.utils.FileUtil;

import java.io.File;

public class NuevaPublicacion extends AppCompatActivity {
    ImageView mImageViewPost;
    private final int GALLERY_REQUIEST_CODE=1;
    File mImageFile;
    Button mButtonPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_publicacion);

        //Accion al hacer click en publicar
        mButtonPost= findViewById(R.id.publicarPost);
        mButtonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { saveImage(); }
        });
        //Accion para abrir la galeria
        mImageViewPost= findViewById(R.id.addImage);
        mImageViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private void saveImage() {

    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(gallery,GALLERY_REQUIEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUIEST_CODE&& resultCode==RESULT_OK){
            try {
                mImageFile = FileUtil.from(this,data.getData());
                mImageViewPost.setImageBitmap(BitmapFactory.decodeFile(mImageFile.getAbsolutePath()));
            }catch (Exception a){
                Toast.makeText(this, "Se produjo un error", Toast.LENGTH_SHORT).show();
            }

        }
    }
}