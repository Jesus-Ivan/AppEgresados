package com.example.pruebarecyclerview.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.pruebarecyclerview.R;
import com.example.pruebarecyclerview.models.User;
import com.example.pruebarecyclerview.providers.AuthProvider;
import com.example.pruebarecyclerview.providers.UserProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dmax.dialog.SpotsDialog;

public class Registro extends AppCompatActivity {

    //variables del boton para saber si estan titulados
    ToggleButton tgbtn;
    boolean titulado=false;

    //Variables para los campos
    EditText nombre;
    EditText apellidoP;
    EditText apellidoM;
    EditText correo;
    EditText telefono;
    EditText noControl;
    Spinner spLista;
    EditText ocupacion;
    EditText lugarOcupacion;
    Button btnRegister;
    EditText contraseña;
    EditText confirmarContraseña;

    AuthProvider mAuthProvider;
    UserProvider mUsersProvider;
    AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //inicializar las variables de las cajas de texto, spinner y togglebutton
        initComponents();

        //Lista con las opciones de carreras
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.lista, android.R.layout.simple_list_item_1);
        spLista.setAdapter(adapter);

        //Al hacer clik al boton registrar realizar:
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();

            }
            
        });

    }

    private void register() {
        String dataNombre=nombre.getText().toString();
        String dataApellidoP= apellidoP.getText().toString();
        String dataApelidoMaterno= apellidoM.getText().toString();
        String dataCorreo= correo.getText().toString();
        String dataTelefono= telefono.getText().toString();
        String dataControl= noControl.getText().toString();
        String dataOcupacion= ocupacion.getText().toString();
        String dataLugarOcupacion= lugarOcupacion.getText().toString();
        String dataSpLista=(String)spLista.getSelectedItem();
        String dataContraseña= contraseña.getText().toString();
        String dataConfirmarContraseña= confirmarContraseña.getText().toString();

        //validacion de todos los campos
        if (!dataNombre.isEmpty() && !dataApellidoP.isEmpty() &&  !dataApelidoMaterno.isEmpty() && !dataCorreo.isEmpty() && !dataTelefono.isEmpty() && !dataControl.isEmpty() && !dataOcupacion.isEmpty() && !dataLugarOcupacion.isEmpty() && !dataSpLista.isEmpty() && !dataContraseña.isEmpty() && !dataConfirmarContraseña.isEmpty() ) {
            if(!isEmailValid(dataCorreo)){
                Toast.makeText(this, "Correo no valido", Toast.LENGTH_SHORT).show();

            }else {
                //Actions to realize when everything is ok
                if(dataContraseña.equals(dataConfirmarContraseña)){
                    if (dataContraseña.length()>=6){
                        //Creando el usuario en Firebase Auth
                        createUser(dataNombre,dataApellidoP,dataApelidoMaterno,dataCorreo,dataTelefono,dataControl,dataOcupacion,dataLugarOcupacion,dataSpLista,titulado,dataContraseña);
                    }else {
                        Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        }else {
            Toast.makeText(this, "Por favor rellena todos los campos", Toast.LENGTH_SHORT).show();

        }
            



    }

    private void createUser(final String Nombre,final String ApellidoP,final String ApellidoM,final String email
                            ,final String Telefono,final String NoControl,final String Ocupacion,final String LugarOcupacion,final String Carrera,final boolean Titulado,final String password) {
        mDialog.show();
        mAuthProvider.register(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Almacenando la informacion del usuario
                    String id =mAuthProvider.getUid();

                    //Agreando informacion del usuario en el objeto correspondiente
                    User user = new User();
                    user.setNombre(Nombre);
                    user.setApellidoP(ApellidoP);
                    user.setApelidoMaterno(ApellidoM);
                    user.setCorreo(email);
                    user.setTelefono(Telefono);
                    user.setControl(NoControl);
                    user.setOcupacion(Ocupacion);
                    user.setLugarOcupacion(LugarOcupacion);
                    user.setCarrera(Carrera);
                    user.setTitulo(Titulado);
                    user.setId(id);

                    mUsersProvider.create(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            mDialog.dismiss();
                            if (task.isSuccessful()){
                                Intent intent= new Intent(Registro.this,InterfazPrinc.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else {
                                Toast.makeText(Registro.this, "No se pudo almancenar el usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                }else {
                    mDialog.dismiss();
                    Toast.makeText(Registro.this, "Registro erroneo", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean isEmailValid(String email) {
        String expression = "^L\\d{8}@tehuacan\\.tecnm\\.mx$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private void initComponents() {
        nombre= findViewById(R.id.editTextTextPersonName);
        apellidoP= findViewById(R.id.editTextTextPersonName9);
        apellidoM= findViewById(R.id.editTextTextPersonName8);
        correo= findViewById(R.id.editTextTextPersonName2);
        telefono= findViewById(R.id.editTextTextPersonName3);
        noControl= findViewById(R.id.editTextTextPersonName4);
        ocupacion= findViewById(R.id.editTextTextPersonName5);
        lugarOcupacion= findViewById(R.id.editTextTextPersonName6);
        spLista=(Spinner)findViewById(R.id.spinner1);
        tgbtn= findViewById(R.id.toggleButton);
        btnRegister= findViewById(R.id.buttonRegister);
        contraseña= findViewById(R.id.editTextTextPassword2);
        confirmarContraseña= findViewById(R.id.editTextTextPassword3);
        mAuthProvider= new AuthProvider();
        mUsersProvider=new UserProvider();
        mDialog = new  SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .setCancelable(false)
                .build();
    }

    //Validar el togglebuton, para saber si tiene titulo o no
    public void onclick(View view) {
        if(view.getId()==R.id.toggleButton){
            if (tgbtn.isChecked()){
                titulado=true;
            }else{
                titulado=false;
            }
        }
    }


}