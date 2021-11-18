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

public class CtrAdapter extends RecyclerView.Adapter<CtrAdapter.ViewHolder> {

    private List<ItemCtrPub> mData;
    private LayoutInflater mInflater;
    private Context context;


    public CtrAdapter(List<ItemCtrPub> itemList, Context context){
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        this.mData=itemList;
    }

    @NonNull
    @NotNull
    @Override
    public CtrAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_pub,null);
        return new CtrAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public void setItems(List<ItemCtrPub> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView texto;

        ViewHolder(View itemView){
            super(itemView);
            texto= itemView.findViewById(R.id.ctrText);
            imagen=itemView.findViewById(R.id.ctrImage);
        }
        void bindData(final ItemCtrPub item){
            texto.setText(item.getTexto());
            imagen.setImageResource(item.getImagen());
        }
    }
}
