package com.vedruna.tfg.GestionHorario;

import com.vedruna.tfg.GestionHorario.Security.DTO.LoginDTO;
import com.vedruna.tfg.GestionHorario.webapp.controller.UsuarioController;
import com.vedruna.tfg.GestionHorario.webapp.dto.NuevaContrasenaDTO;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioRepository;
import com.vedruna.tfg.GestionHorario.webapp.service.UsuarioPasswordService;
import com.vedruna.tfg.GestionHorario.webapp.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioPasswordService usuarioPasswordService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarEntrada() {
        LoginDTO loginDTO = new LoginDTO("john@example.com", "password");
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setCorreo("john@example.com");

        when(usuarioRepository.findByCorreo(loginDTO.getCorreo())).thenReturn(usuario);

        ResponseEntity<Void> response = usuarioController.registrarEntrada(loginDTO);

        verify(usuarioService).registrarEntrada(usuario.getId());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testRegistrarSalida() {
        LoginDTO loginDTO = new LoginDTO("john@example.com", "password");
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setCorreo("john@example.com");

        when(usuarioRepository.findByCorreo(loginDTO.getCorreo())).thenReturn(usuario);

        ResponseEntity<Void> response = usuarioController.registrarSalida(loginDTO);

        verify(usuarioService).registrarSalida(usuario.getId());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testActualizarContraseña() {
        NuevaContrasenaDTO nuevaContrasenaDTO = new NuevaContrasenaDTO("john@example.com", "newpassword");
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setCorreo("john@example.com");

        when(usuarioRepository.findByCorreo(nuevaContrasenaDTO.getCorreo())).thenReturn(usuario);

        ResponseEntity<Void> response = usuarioController.actualizarContrasena(nuevaContrasenaDTO);

        verify(usuarioPasswordService).actualizarContrasena(usuario.getId(), nuevaContrasenaDTO.getNuevacontrasena());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
