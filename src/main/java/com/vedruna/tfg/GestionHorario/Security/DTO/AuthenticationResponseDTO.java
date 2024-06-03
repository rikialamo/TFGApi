package com.vedruna.tfg.GestionHorario.Security.DTO;

import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponseDTO {
	/**
	 * Token de autenticación generado.
	 */
	private String token;

	private Usuario usuario;
}
