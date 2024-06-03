package com.vedruna.tfg.GestionHorario;

import com.vedruna.tfg.GestionHorario.Security.DTO.LoginDTO;
import com.vedruna.tfg.GestionHorario.webapp.controller.AdminController;
import com.vedruna.tfg.GestionHorario.webapp.dto.DateDTO;
import com.vedruna.tfg.GestionHorario.webapp.dto.UsuarioDTO;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import com.vedruna.tfg.GestionHorario.webapp.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

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

        when(adminService.crearUsuario(usuarioDTO)).thenReturn(usuario);

        ResponseEntity<Usuario> response = adminController.crearUsuario(usuarioDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getNombre());
    }

    @Test
    void testCrearAdmin() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("Admin", "admin@example.com", "adminpassword");
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Admin");
        usuario.setCorreo("admin@example.com");

        when(adminService.crearAdmin(usuarioDTO)).thenReturn(usuario);

        ResponseEntity<Usuario> response = adminController.crearAdmin(usuarioDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Admin", response.getBody().getNombre());
    }

    @Test
    void testActualizarUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("John Updated", "john@example.com", "newpassword");
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("John Updated");
        usuario.setCorreo("john@example.com");

        when(adminService.actualizarUsuario(usuarioDTO)).thenReturn(usuario);

        ResponseEntity<Usuario> response = adminController.actualizarUsuario(usuarioDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Updated", response.getBody().getNombre());
    }

    @Test
    void testEliminarUsuario() {
        LoginDTO loginDTO = new LoginDTO("john@example.com", "password");

        doNothing().when(adminService).eliminarUsuario(loginDTO);

        ResponseEntity<Void> response = adminController.eliminarUsuario(loginDTO);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(adminService, times(1)).eliminarUsuario(loginDTO);
    }

    @Test
    void testListarUsuarios() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());

        when(adminService.listarUsuarios()).thenReturn(usuarios);

        ResponseEntity<List<Usuario>> response = adminController.listarUsuarios();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testListarUsers() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());

        when(adminService.listarUsuariosUser()).thenReturn(usuarios);

        ResponseEntity<List<Usuario>> response = adminController.listarUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testListarAdmins() {
        List<Usuario> admins = Arrays.asList(new Usuario(), new Usuario());

        when(adminService.listarUsuariosAdmin()).thenReturn(admins);

        ResponseEntity<List<Usuario>> response = adminController.listarAdmins();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetUsuariosSinFichar() {
        DateDTO dateDTO = new DateDTO();  // Example values for DateDTO
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());

        when(adminService.getUsuariosSinFichar(dateDTO)).thenReturn(usuarios);

        List<Usuario> result = adminController.getUsuariosSinFichar(dateDTO);

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
