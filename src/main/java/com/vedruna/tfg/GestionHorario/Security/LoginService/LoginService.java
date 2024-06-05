package com.vedruna.tfg.GestionHorario.Security.LoginService;

import com.vedruna.tfg.GestionHorario.Security.DTO.AuthenticationResponseDTO;
import com.vedruna.tfg.GestionHorario.Security.DTO.LoginDTO;

import com.vedruna.tfg.GestionHorario.Security.Exceptions.IncorrectPasswordException;
import com.vedruna.tfg.GestionHorario.Security.Exceptions.UnauthorizedException;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.UsuarioTipoUser;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioRepository;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioTipoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioTipoUserRepository usuarioTipoUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationResponseDTO adminLogin(LoginDTO loginDTO) {

        Usuario usuario = usuarioRepository.findByCorreo(loginDTO.getCorreo());
        UsuarioTipoUser usuarioTipoUser = usuarioTipoUserRepository.findByUsuarioId(usuario.getId());
        if (usuarioTipoUser.getTipoUser().getId() == 1L) {

            if (passwordEncoder.matches(loginDTO.getContrasena(), usuario.getContraseña())) {

                //token
                return new AuthenticationResponseDTO("");

            } else {
                throw new IncorrectPasswordException();
            }

        } else {
            throw new UnauthorizedException();
        }
    }

    public AuthenticationResponseDTO userLogin(LoginDTO loginDTO) {

        Usuario usuario = usuarioRepository.findByCorreo(loginDTO.getCorreo());

        if (passwordEncoder.matches(loginDTO.getContrasena(), usuario.getContraseña())) {

            return new AuthenticationResponseDTO("");

        } else {
            throw new IncorrectPasswordException();
        }
    }
}
