package com.example.pruebarecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PubAdapter2 extends RecyclerView.Adapter<PubAdapter2.ViewHolder> {
    private List<ItemPub2> mData;
    private LayoutInflater mInflater;
    private Context context;


    public PubAdapter2(List<ItemPub2> itemList,Context context){
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        this.mData=itemList;
    }

    @Override
    public PubAdapter2.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_pub2,null);

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
