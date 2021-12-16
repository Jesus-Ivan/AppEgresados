package com.example.pruebarecyclerview.models;

public class Post {

    private String descripcion;
    private String imagen;
    private String idUser;
    private String id;
    private String category;

    public Post(String descripcion, String imagen, String idUser, String id, String category) {
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.idUser = idUser;
        this.id = id;
        this.category = category;
    }
    public Post(){

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
