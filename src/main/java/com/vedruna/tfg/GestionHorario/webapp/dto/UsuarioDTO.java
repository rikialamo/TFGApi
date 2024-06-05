package com.vedruna.tfg.GestionHorario.webapp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO {

    private String nombre;
    private String correo;
    private String contrasena;

    public UsuarioDTO(String contrasena, String correo, String nombre ) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

}

