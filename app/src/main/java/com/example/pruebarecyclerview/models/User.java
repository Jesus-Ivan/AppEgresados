package com.example.pruebarecyclerview.models;

public class User {
    String nombre;
    String apellidoP;
    String apelidoMaterno;
    String correo;
    String telefono;
    String control;
    String ocupacion;
    String lugarOcupacion;
    String carrera;
    boolean titulo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String contraseña;
    String id;

    public User(){

    }

    public User(String nombre, String apellidoP, String apelidoMaterno, String correo, String telefono, String control, String ocupacion, String lugarOcupacion, String carrera, String contraseña, boolean titulo) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apelidoMaterno = apelidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.control = control;
        this.ocupacion = ocupacion;
        this.lugarOcupacion = lugarOcupacion;
        this.carrera = carrera;
        this.contraseña = contraseña;
        this.titulo=titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApelidoMaterno() {
        return apelidoMaterno;
    }

    public void setApelidoMaterno(String apelidoMaterno) {
        this.apelidoMaterno = apelidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getLugarOcupacion() {
        return lugarOcupacion;
    }

    public void setLugarOcupacion(String lugarOcupacion) {
        this.lugarOcupacion = lugarOcupacion;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean getTitulo() {
        return titulo;
    }

    public void setTitulo(boolean titulo) {
        this.titulo = titulo;
    }
}
