package com.example.pruebarecyclerview;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CtrAdapter extends RecyclerView.Adapter<CtrAdapter.ViewHolder> implements View.OnClickListener{

    private List<ItemCtrPub> mData;
    private LayoutInflater mInflater;
    private Context context;

    private View.OnClickListener listener;


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

        //Escuchar el evento click de la lista
        view.setOnClickListener(this);

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


    //metodo de apollo para el elemento seleccionado
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
                listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imagen;
        TextView texto;
        ImageButton imageButton_options;


        private static final  String Tag="xd";
        ViewHolder(View itemView){
            super(itemView);
            texto= itemView.findViewById(R.id.ctrText);
            imagen=itemView.findViewById(R.id.ctrImage);
            imageButton_options=itemView.findViewById(R.id.imageButton4);
            imageButton_options.setOnClickListener(this);
        }
        void bindData(final ItemCtrPub item){
            texto.setText(item.getTexto());
            imagen.setImageResource(item.getImagen());
        }

        @Override
        public void onClick(View view) {
            Log.d(Tag,"on clik "+getAdapterPosition());
            showPopupMenu(view);
        }

        private void showPopupMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.show();
        }
    }
}
