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

public class PubPendientesAdapter extends RecyclerView.Adapter<PubPendientesAdapter.ViewHolder>{

    private List<ItemPub2> mData;
    private LayoutInflater mInflater;
    private Context context;

    public PubPendientesAdapter(List<ItemPub2> itemList,Context context){
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        this.mData=itemList;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_pendientes,null);

        return new PubPendientesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagen;
        TextView nombre, texto;

        ViewHolder( View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.textView22);
            texto= itemView.findViewById(R.id.textView23);
            imagen=itemView.findViewById(R.id.imageView3);
        }

        public void bindData(final ItemPub2 item) {
            nombre.setText(item.getNombre());
            texto.setText(item.getTexto());
            imagen.setImageResource(item.getImagen());
        }
    }
}
