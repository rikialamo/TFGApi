package com.vedruna.tfg.GestionHorario;

import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.HorarioRepository;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioRepository;
import com.vedruna.tfg.GestionHorario.webapp.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private HorarioRepository horarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegistrarEntrada() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(java.util.Optional.of(usuario));

        usuarioService.registrarEntrada(1L);

        verify(horarioRepository).save(any());
    }

    @Test
    void testRegistrarSalida() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(java.util.Optional.of(usuario));

        usuarioService.registrarSalida(1L);

        verify(horarioRepository).save(any());
    }
}
