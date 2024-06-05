package com.vedruna.tfg.GestionHorario.Security.DTO;

import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponseDTO {
	private String token;
}
