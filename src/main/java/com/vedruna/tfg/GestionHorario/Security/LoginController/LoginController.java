package com.vedruna.tfg.GestionHorario.Security.LoginController;

import com.vedruna.tfg.GestionHorario.Security.DTO.AuthenticationResponseDTO;
import com.vedruna.tfg.GestionHorario.Security.DTO.LoginDTO;
import com.vedruna.tfg.GestionHorario.Security.LoginService.LoginService;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioRepository;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioTipoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioTipoUserRepository usuarioTipoUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("admin")
    public ResponseEntity<AuthenticationResponseDTO> adminLogin(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(loginService.adminLogin(loginDTO));
    }

    @PostMapping("user")
    public ResponseEntity<AuthenticationResponseDTO> userLogin(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(loginService.userLogin(loginDTO));
    }
}
