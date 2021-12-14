package com.example.pruebarecyclerview.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pruebarecyclerview.R;
import com.example.pruebarecyclerview.activities.ControlPub;
import com.example.pruebarecyclerview.activities.MyProfile;
import com.example.pruebarecyclerview.activities.Opc;
import com.example.pruebarecyclerview.activities.OpcAdapter;
import com.example.pruebarecyclerview.activities.PubPendientes;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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

    List<Opc> lst;
    private List<Opc> getData() {

        lst = new ArrayList<>();

        lst.add(new Opc(1, R.drawable.ic_user_edit,"Modificar mi perfil"));
        lst.add(new Opc(2,R.drawable.ic_control,"Control de publicaciones"));
        lst.add(new Opc(3,R.drawable.ic_pendient,"Publicaciones pendientes"));
        lst.add(new Opc(4,R.drawable.ic_logout,"Cerrar sesion"));
        lst.add(new Opc(5,R.drawable.ic_delete_user,"Eliminar mi cuenta"));
        return lst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_second, container, false);

        ListView listaopciones=(ListView) view.findViewById(R.id.listOptions);


        listaopciones=view.findViewById(R.id.listOptions);

        OpcAdapter adapter = new OpcAdapter(this.getContext(), getData());
        listaopciones.setAdapter(adapter);

        listaopciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Opc c= lst.get(i);
                Intent pub=null;
                switch ( c.getId()){
                    case 1:
                         pub = new Intent(getActivity(), MyProfile.class);
                        break;
                    case 2:
                        pub = new Intent(getActivity(), ControlPub.class);
                        break;
                    case 3:
                        pub = new Intent(getActivity(), PubPendientes.class);
                        break;
                    case 4:
                        pub = new Intent(getActivity(),MyProfile.class);
                        break;
                    case 5:
                        pub = new Intent(getActivity(),MyProfile.class);
                        break;
                }
                startActivity(pub);
                Toast.makeText(getContext(),c.nombre,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}