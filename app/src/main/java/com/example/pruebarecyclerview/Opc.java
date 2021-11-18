package com.example.pruebarecyclerview;

public class Opc {

    //Atributos
    public int id;
    public int image;
    public String nombre;

    public Opc(int id, int image, String nombre) {
        this.id = id;
        this.image = image;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
