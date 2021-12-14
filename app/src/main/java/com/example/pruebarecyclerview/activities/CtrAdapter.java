package com.example.pruebarecyclerview.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebarecyclerview.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CtrAdapter extends RecyclerView.Adapter<CtrAdapter.ViewHolder> implements View.OnClickListener{

    private List<ItemCtrPub> mData;
    private LayoutInflater mInflater;
    private Context context;
    Dialog dialog;

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

        //creamos el objeto para cuadro de dialogo de "eliminar " en el popup menu
        dialog= new Dialog(context);

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
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
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Intent pub=null;
            //Acciones a realizar al hacer clic en el menu PopUp
            switch (item.getItemId()){
                case R.id.action_edit:
                    pub = new Intent(context,NuevaPublicacion.class);
                    context.startActivity(pub);
                    return true;
                case R.id.action_hide:


                    return true;
                case R.id.action_delete:
                    openWarningDialog();
                    return true;
                default:
                    return false;
            }
        }

        private void openWarningDialog() {
            dialog.setContentView(R.layout.dialog_warning);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            Button button7 =dialog.findViewById(R.id.button7);
            Button button6 =dialog.findViewById(R.id.button6);
            dialog.show();

            //Accion a realizar al "NO eliminar publicacion"
            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

        }
    }
}
