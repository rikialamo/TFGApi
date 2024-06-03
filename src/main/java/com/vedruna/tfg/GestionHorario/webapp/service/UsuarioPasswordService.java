package com.vedruna.tfg.GestionHorario.webapp.service;

import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioPasswordService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void actualizarContraseña(Long usuarioId, String nuevaContraseña) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String contraseñaCodificada = passwordEncoder.encode(nuevaContraseña);
        usuario.setContraseña(contraseñaCodificada);
        usuarioRepository.save(usuario);
    }
}
