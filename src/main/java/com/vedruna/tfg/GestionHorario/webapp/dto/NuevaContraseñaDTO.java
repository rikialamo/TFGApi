package com.vedruna.tfg.GestionHorario.webapp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NuevaContraseñaDTO {

    private String correo;
    private String nuevacontrasena;

    // Constructor, getters y setters

    public NuevaContraseñaDTO(String correo, String nuevacontrasena) {
        this.correo = correo;
        this.nuevacontrasena = nuevacontrasena;
    }

}
