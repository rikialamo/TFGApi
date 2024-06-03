package com.vedruna.tfg.GestionHorario.webapp.controller;


import com.vedruna.tfg.GestionHorario.Security.DTO.LoginDTO;
import com.vedruna.tfg.GestionHorario.webapp.dto.NuevaContraseñaDTO;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioRepository;
import com.vedruna.tfg.GestionHorario.webapp.service.UsuarioPasswordService;
import com.vedruna.tfg.GestionHorario.webapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private  UsuarioService usuarioService;

    @Autowired
    private UsuarioPasswordService usuarioPasswordService;

    @PostMapping("/entrada")
    public ResponseEntity<Void> registrarEntrada(@RequestBody LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByCorreo(loginDTO.getCorreo());
        usuarioService.registrarEntrada(usuario.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/salida")
    public ResponseEntity<Void> registrarSalida(@RequestBody LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByCorreo(loginDTO.getCorreo());
        usuarioService.registrarSalida(usuario.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/actualizarContrasena")
    public ResponseEntity<Void> actualizarContraseña(@RequestBody NuevaContraseñaDTO NuevaContraseñaDTO) {
        Usuario usuario = usuarioRepository.findByCorreo(NuevaContraseñaDTO.getCorreo());
        usuarioPasswordService.actualizarContraseña(usuario.getId(), NuevaContraseñaDTO.getNuevacontrasena());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

