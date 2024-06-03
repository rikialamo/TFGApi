package com.vedruna.tfg.GestionHorario.webapp.dto;

public class UsuarioDTO {

    private String nombre;
    private String correo;
    private String contrasena;

    public UsuarioDTO(String contrasena, String correo, String nombre ) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

