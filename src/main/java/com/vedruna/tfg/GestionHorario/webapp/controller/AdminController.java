package com.vedruna.tfg.GestionHorario.webapp.controller;

import com.vedruna.tfg.GestionHorario.Security.DTO.LoginDTO;
import com.vedruna.tfg.GestionHorario.webapp.dto.DateDTO;
import com.vedruna.tfg.GestionHorario.webapp.dto.UsuarioDTO;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import com.vedruna.tfg.GestionHorario.webapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;


    @PostMapping("/usuario")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario nuevoUsuario = adminService.crearUsuario(usuarioDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/admin")
    public ResponseEntity<Usuario> crearAdmin(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario nuevoUsuario = adminService.crearAdmin(usuarioDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarUsuario")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioActualizado = adminService.actualizarUsuario(usuarioDTO);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/eliminarUsuario")
    public ResponseEntity<Void> eliminarUsuario(@RequestBody LoginDTO loginDTO) {
        adminService.eliminarUsuario(loginDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = adminService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> listarUsers() {
        List<Usuario> usuarios = adminService.listarUsuariosUser();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<Usuario>> listarAdmins() {
        List<Usuario> admins = adminService.listarUsuariosAdmin();
        return ResponseEntity.ok(admins);
    }


    @PostMapping("/sin-fichaje-semana-anterior")
    public List<Usuario> getUsuariosSinFichar(@RequestBody DateDTO dateDTO) {
        return adminService.getUsuariosSinFichar(dateDTO);
    }
}
