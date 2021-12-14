package com.example.pruebarecyclerview.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebarecyclerview.R;
import com.example.pruebarecyclerview.activities.ItemPub2;
import com.example.pruebarecyclerview.activities.NuevaPublicacion;
import com.example.pruebarecyclerview.activities.PubAdapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View mView;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =inflater.inflate(R.layout.fragment_first, container, false);
        initPub(mView);

        //Cuando hace click en floating action button
        ImageButton nuevaPub =(ImageButton)mView.findViewById(R.id.imageButton2);
        nuevaPub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pub = new Intent(getContext(), NuevaPublicacion.class);
                startActivity(pub);
            }
        });

        return mView;
    }

    private void initPub(View view) {
        List<ItemPub2> elements= new ArrayList<>();
        elements.add(new ItemPub2("Moises Rodriguez Bolaños","Hola amigos, soy egresado del tecnologico de tehuacan. Actualmente estoy laborando en BonIce Inc. y tengo unas ofertas de empleo bien prronsisimas alv, me preguntaba si alguien de ustedes estaria interesado en trabajar",R.drawable.img_null,R.drawable.ic_titulo_2));
        elements.add(new ItemPub2("Perez Lechuga Jesus Ivan","Hola amigos, soy egresado del tecnologico de tehuacan. Actualmente estoy laboran", R.drawable.bolsa_2,R.drawable.ic_titulo_2));
        elements.add(new ItemPub2("Velez silva Jose Manuel","Hola amigos, soy egresado del tecnologico de tehuacan. ", android.R.drawable.sym_def_app_icon,R.drawable.ic_titulo_2));
        elements.add(new ItemPub2("Yolanda Iridian Ramirez Lerdo","Hola amigos, soy egresado del tecnologico de tehuacan. Actualmente estoy laborando en BonIce Inc. y tengo unas ofertas de empleo bien prronsisimas alv, me preguntaba si alguien de ustedes estaria interesado en trabajar",R.drawable.img_null,R.drawable.ic_sin_titulo));
        elements.add(new ItemPub2("Daniel Valerio Rosales","Hola amigos, ",R.drawable.hyper_x,R.drawable.ic_titulo_2));
        elements.add(new ItemPub2("Otro Moises","Hola amigos, soy egresado del tec",R.drawable.bolsa_trabajo,R.drawable.ic_titulo_2));

        PubAdapter2 pubAdapterImg = new PubAdapter2(elements,getContext());
        RecyclerView incio= view.findViewById(R.id.rv_inicio);

        //Implementacion de las acciones al hacer clic en alguna publicacion del inicio
        pubAdapterImg.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Seleccion "+elements.get(incio.getChildAdapterPosition(view)).getNombre(),Toast.LENGTH_LONG).show();

            }
        });

        incio.setLayoutManager(new LinearLayoutManager(getContext()));
        incio.setAdapter(pubAdapterImg);


    }


}