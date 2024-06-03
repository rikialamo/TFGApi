package com.vedruna.tfg.GestionHorario;

import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioRepository;
import com.vedruna.tfg.GestionHorario.webapp.service.UsuarioPasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioPasswordServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioPasswordService usuarioPasswordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testActualizarContraseña() {
        Long usuarioId = 1L;
        String nuevaContraseña = "nuevaContraseña";
        String contraseñaCodificada = "contraseñaCodificada";

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setContraseña("contraseñaAntigua");

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(passwordEncoder.encode(nuevaContraseña)).thenReturn(contraseñaCodificada);

        usuarioPasswordService.actualizarContraseña(usuarioId, nuevaContraseña);

        verify(passwordEncoder).encode(nuevaContraseña);
        assertEquals(contraseñaCodificada, usuario.getContraseña());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testActualizarContraseñaUsuarioNoEncontrado() {
        Long usuarioId = 1L;
        String nuevaContraseña = "nuevaContraseña";

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioPasswordService.actualizarContraseña(usuarioId, nuevaContraseña);
        });

        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(passwordEncoder, never()).encode(anyString());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}
