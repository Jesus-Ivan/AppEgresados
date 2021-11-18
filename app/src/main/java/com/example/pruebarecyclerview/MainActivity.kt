package com.example.pruebarecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.security.Principal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_ingresar : Button = findViewById(R.id.button_ingresar)
        btn_ingresar.setOnClickListener{
            val Principal= Intent(this,
                InterfazPrinc::class.java)
            startActivity(Principal);
        }

        val lbl_registrarse : TextView = findViewById(R.id.textView4)
        lbl_registrarse.setOnClickListener{
            val Registrar= Intent(this,
                Registro::class.java)
            startActivity(Registrar);
        }


    }




}