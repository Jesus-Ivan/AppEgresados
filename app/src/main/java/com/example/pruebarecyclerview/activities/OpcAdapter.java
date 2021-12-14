package com.example.pruebarecyclerview.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pruebarecyclerview.R;

import java.util.List;

public class OpcAdapter extends BaseAdapter {
    Context context;
    List<Opc>lst;

    public OpcAdapter(Context context, List<Opc> lst) {
        this.context = context;
        this.lst = lst;
    }


    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageOpc;
        TextView textOpc;

        Opc c= lst.get(i);

        if(view==null)
            view= LayoutInflater.from(context).inflate(R.layout.item_opcion,null);


        imageOpc=view.findViewById(R.id.img_opc);
        textOpc=view.findViewById(R.id.opc);

        imageOpc.setImageResource(c.image);
        textOpc.setText(c.nombre);
        return view;
    }
}
