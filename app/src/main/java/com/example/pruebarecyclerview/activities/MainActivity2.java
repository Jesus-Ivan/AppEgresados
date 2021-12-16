package com.example.pruebarecyclerview.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebarecyclerview.R;
import com.example.pruebarecyclerview.providers.AuthProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import dmax.dialog.SpotsDialog;

public class MainActivity2 extends AppCompatActivity {

    //Variables globales
    TextView mTextViewRegister;
    EditText mTextInputEmail;
    EditText mTextInputPassword;
    Button mButtonLogin;
    AuthProvider mAuth;
    AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initComponents();

        //onclik para acceder a la pantalla del registro de usuario
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,Registro.class);
                startActivity(intent);
            }
        });


        //onclick para tratar de iniciar sesion
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    private void login(){
        String email=mTextInputEmail.getText().toString();
        String passsword=mTextInputPassword.getText().toString();
        mDialog.show();
        try {
            mAuth.login(email,passsword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    mDialog.dismiss();
                    if (task.isSuccessful()){
                        Intent intent= new Intent(MainActivity2.this,InterfazPrinc.class);
                        startActivity(intent);
                    }else {
                        toastError("Email o contraseña no validos");
                        //Toast.makeText(MainActivity2.this, "Email o contraseña no validos", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }catch (Exception log){
            mDialog.dismiss();
            toastError("Campos no validos");
            //Toast.makeText(MainActivity2.this, "Campos no validos", Toast.LENGTH_LONG).show();
        }

    }

    private void initComponents(){
        mTextViewRegister= findViewById(R.id.textView4);
        mTextInputEmail= findViewById(R.id.editTextTextEmailAddress);
        mTextInputPassword= findViewById(R.id.editTextTextPassword);
        mButtonLogin= findViewById(R.id.button_ingresar);
        mAuth= new AuthProvider();
        mDialog = new  SpotsDialog.Builder()
                .setContext(this)
                .setTheme(R.style.Custom)
                .setCancelable(false)
               .build();
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