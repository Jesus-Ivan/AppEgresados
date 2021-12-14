package com.example.pruebarecyclerview.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebarecyclerview.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PubAdapter2 extends RecyclerView.Adapter<PubAdapter2.ViewHolder> implements View.OnClickListener {
    private List<ItemPub2> mData;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;


    public PubAdapter2(List<ItemPub2> itemList,Context context){
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        this.mData=itemList;
    }

    @Override
    public PubAdapter2.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_pub2,null);

        //Haciendo que el listener reciba alguna instruccion
        view.setOnClickListener(this);
        return new PubAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setItems(List<ItemPub2> items){mData=items;}



    //Metodo de apoyo para el recyclerview
    public void setOnclickListener(View.OnClickListener listener){
        this.listener= listener;
    }

    //Metodo onlick de la interfaz, para hacer que funcione un elemento dentro del inicio(feed)
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView titulo,imagen;
        TextView nombre, texto;

        ViewHolder(View itemView){
            super(itemView);
            titulo=itemView.findViewById(R.id.ctrImage);
            nombre= itemView.findViewById(R.id.textView21);
            texto= itemView.findViewById(R.id.ctrText);
            imagen=itemView.findViewById(R.id.imagenPub);
        }
        void bindData(final ItemPub2 item){
            titulo.setImageResource(item.getTitulo());
            nombre.setText(item.getNombre());
            texto.setText(item.getTexto());
            imagen.setImageResource(item.getImagen());
        }
    }
}
