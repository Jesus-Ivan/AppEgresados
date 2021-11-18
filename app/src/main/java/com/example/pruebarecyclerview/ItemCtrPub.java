package com.example.pruebarecyclerview;

public class ItemCtrPub {
    String texto;
    int imagen;

    public ItemCtrPub(String texto, int imagen) {
        this.texto = texto;
        this.imagen = imagen;
    }


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
