package com.example.pruebarecyclerview.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pruebarecyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class ControlPub extends AppCompatActivity {

    ImageButton popUp;
    RecyclerView recyclerCtr;
    CtrAdapter AdapterCtr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_pub);

        iniciarcontrol();
    }

    private void iniciarcontrol() {
        recyclerCtr=findViewById(R.id.RVmisPub);
        recyclerCtr.setLayoutManager(new LinearLayoutManager(this));

        List<ItemCtrPub> misPubList= new ArrayList<>();


        //con este ciclo se agregaron las publicaciones al control de MIS PUBLICACIONES
        for (int c=0; c<20;c++){
            misPubList.add(new ItemCtrPub("ESTA ES MI PUBLICACION numero: "+c+" aver si funciona", R.drawable.hyper_x));
        }
        AdapterCtr = new CtrAdapter(misPubList,this);

        //Accion a realizar al hacer click en un item del control de publicaciones
        AdapterCtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Seleccion:"+misPubList.get(recyclerCtr.getChildAdapterPosition(view)).texto,Toast.LENGTH_LONG).show();
            }
        });
        recyclerCtr.setAdapter(AdapterCtr);
    }
}