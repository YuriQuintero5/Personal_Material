package com.example.persona_material;

public class Persona {
    private String Cedula;
    private String Nombre;
    private String Apellido;
    private int Foto;
    private String id;


    public Persona (String Cedula, String Nombre, String Apellido, int Foto){
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Foto = Foto;
    }
    public String getId() {
        return id;
    }

    public Persona(){

    }

    public Persona (String Cedula, String Nombre, String Apellido, int Foto, String id){
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Foto = Foto;
        this.id = id;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getFoto() {
        return Foto;
    }

    public void setFoto(int foto) {
        Foto = foto;
    }

    public void guardar(){
        Datos.guardar(this);
    }

    public void eliminar(){
        Datos.eliminar(this);
    }

}
