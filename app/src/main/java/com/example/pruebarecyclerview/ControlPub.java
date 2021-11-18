package com.example.pruebarecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class ControlPub extends AppCompatActivity {

    ImageButton popUp;
    RecyclerView recyclerCtr;
    CtrAdapter AdapterCtr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_pub);

        popUp=findViewById(R.id.imageButton4);

    }
}