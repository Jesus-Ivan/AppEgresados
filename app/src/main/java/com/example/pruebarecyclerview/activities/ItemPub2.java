package com.example.pruebarecyclerview.activities;

public class ItemPub2 {

    String Nombre;
    String texto;
    int imagen;
    int titulo;


    public ItemPub2(String Nombre,String texto,int imagen,int titulo){
        this.Nombre=Nombre;
        this.texto=texto;
        this.imagen=imagen;
        this.titulo=titulo;
    }


    public String getNombre() {
        return Nombre;
    }

    public String getTexto() {
        return texto;
    }

    public int getImagen() {
        return imagen;
    }

    public int getTitulo() {
        return titulo;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setTitulo(int titulo) {
        this.titulo = titulo;
    }
}
