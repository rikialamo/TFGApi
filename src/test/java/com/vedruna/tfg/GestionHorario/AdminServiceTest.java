package com.vedruna.tfg.GestionHorario;

import com.vedruna.tfg.GestionHorario.Security.DTO.LoginDTO;
import com.vedruna.tfg.GestionHorario.Security.Exceptions.UserNotFoundException;
import com.vedruna.tfg.GestionHorario.webapp.dto.DateDTO;
import com.vedruna.tfg.GestionHorario.webapp.dto.UsuarioDTO;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.UsuarioTipoUser;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.HorarioRepository;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioRepository;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioTipoUserRepository;
import com.vedruna.tfg.GestionHorario.webapp.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioTipoUserRepository usuarioTipoUserRepository;

    @Mock
    private HorarioRepository horarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("John Doe", "john@example.com", "password");
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("John Doe");
        usuario.setCorreo("john@example.com");

        when(passwordEncoder.encode(usuarioDTO.getContrasena())).thenReturn("encodedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = adminService.crearUsuario(usuarioDTO);

        assertNotNull(result);
        assertEquals("John Doe", result.getNombre());
        assertEquals("john@example.com", result.getCorreo());

        ArgumentCaptor<Usuario> usuarioCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository).save(usuarioCaptor.capture());
        assertEquals("encodedPassword", usuarioCaptor.getValue().getContraseña());

        verify(usuarioTipoUserRepository).save(any(UsuarioTipoUser.class));
    }

    @Test
    void testCrearAdmin() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Admin", "admin@example.com", "adminpassword");
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Admin");
        usuario.setCorreo("admin@example.com");

        when(passwordEncoder.encode(usuarioDTO.getContrasena())).thenReturn("encodedAdminPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = adminService.crearAdmin(usuarioDTO);

        assertNotNull(result);
        assertEquals("Admin", result.getNombre());
        assertEquals("admin@example.com", result.getCorreo());

        ArgumentCaptor<Usuario> usuarioCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository).save(usuarioCaptor.capture());
        assertEquals("encodedAdminPassword", usuarioCaptor.getValue().getContraseña());

        verify(usuarioTipoUserRepository).save(any(UsuarioTipoUser.class));
    }

    @Test
    void testActualizarUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("newpassword", "john@example.com", "John Updated");
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("John Doe");
        usuario.setCorreo("john@example.com");

        when(usuarioRepository.findByCorreo("john@example.com")).thenReturn(usuario);
        when(passwordEncoder.encode(usuarioDTO.getContrasena())).thenReturn("encodedNewPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = adminService.actualizarUsuario(usuarioDTO);

        assertNotNull(result);
        assertEquals("John Updated", result.getNombre());
        assertEquals("john@example.com", result.getCorreo());
        assertEquals("encodedNewPassword", result.getContraseña());

        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testActualizarUsuarioNotFound() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("John Updated", "john@example.com", "newpassword");
        when(usuarioRepository.findByCorreo("john@example.com")).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            adminService.actualizarUsuario(usuarioDTO);
        });
    }

    @Test
    void testEliminarUsuario() {
        LoginDTO loginDTO = new LoginDTO("john@example.com", "password");
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setCorreo("john@example.com");

        UsuarioTipoUser usuarioTipoUser = new UsuarioTipoUser();
        usuarioTipoUser.setId(1L);
        usuarioTipoUser.setUsuario(usuario);

        when(usuarioRepository.findByCorreo("john@example.com")).thenReturn(usuario);
        when(usuarioTipoUserRepository.findByUsuarioId(1L)).thenReturn(usuarioTipoUser);

        adminService.eliminarUsuario(loginDTO);

        verify(usuarioTipoUserRepository).deleteById(1L);
        verify(horarioRepository).deleteByUsuarioId(1L);
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void testEliminarUsuarioNotFound() {
        LoginDTO loginDTO = new LoginDTO("john@example.com", "password");
        when(usuarioRepository.findByCorreo("john@example.com")).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            adminService.eliminarUsuario(loginDTO);
        });
    }

    @Test
    void testListarUsuarios() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = adminService.listarUsuarios();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testListarUsuariosUser() {
        List<Long> userIds = Arrays.asList(1L, 2L);
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioTipoUserRepository.findByTipoUser("USER")).thenReturn(userIds);
        when(usuarioRepository.findAllById(userIds)).thenReturn(usuarios);

        List<Usuario> result = adminService.listarUsuariosUser();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testListarUsuariosAdmin() {
        List<Long> adminIds = Arrays.asList(1L, 2L);
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioTipoUserRepository.findByTipoUser("ADMIN")).thenReturn(adminIds);
        when(usuarioRepository.findAllById(adminIds)).thenReturn(usuarios);

        List<Usuario> result = adminService.listarUsuariosAdmin();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetUsuariosSinFicharNotNull() {
        DateDTO dateDTO = mock(DateDTO.class);
        LocalDate inicio = LocalDate.of(2024, 5, 1);
        LocalDate fin = LocalDate.of(2024, 5, 7);

        when(dateDTO.validarFecha(5, 1)).thenReturn(inicio);
        when(dateDTO.validarFecha(5, 7)).thenReturn(fin);

        List<Usuario> result = adminService.getUsuariosSinFichar(dateDTO);

        assertNotNull(result);
    }
}
