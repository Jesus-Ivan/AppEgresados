package com.example.pruebarecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PubPendientes extends AppCompatActivity {

    RecyclerView recyclerCtr;
    PubPendientesAdapter AdapterPendientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub_pendientes);
        iniciarcontrol();
    }


    private void iniciarcontrol() {
        recyclerCtr=findViewById(R.id.RV_PubPendientes);
        recyclerCtr.setLayoutManager(new LinearLayoutManager(this));

        List<ItemPub2> misPubList= new ArrayList<>();

        for (int c=0; c<20;c++){
            misPubList.add(new ItemPub2("Persona numero "+c,"Hola este es un mensaje dee prueba :v",R.drawable.bolsa_trabajo,R.drawable.not_image_pub));
        }
        AdapterPendientes = new PubPendientesAdapter(misPubList,this);

        /*Accion a realizar al hacer click en un item del control de publicaciones
        AdapterPendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Seleccion:"+misPubList.get(recyclerCtr.getChildAdapterPosition(view)).texto,Toast.LENGTH_LONG).show();
            }
        });
        */
        recyclerCtr.setAdapter(AdapterPendientes);
    }
}