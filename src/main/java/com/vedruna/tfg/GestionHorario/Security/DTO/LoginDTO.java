package com.vedruna.tfg.GestionHorario.Security.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {

    private String correo;
    private String contrasena;

    // Constructor, getters y setters

    public LoginDTO(String correo, String contraseña) {
        this.correo = correo;
        this.contrasena = contraseña;
    }

}


