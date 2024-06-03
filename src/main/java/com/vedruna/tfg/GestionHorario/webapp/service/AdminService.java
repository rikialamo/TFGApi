package com.vedruna.tfg.GestionHorario.webapp.service;

import com.vedruna.tfg.GestionHorario.Security.DTO.LoginDTO;
import com.vedruna.tfg.GestionHorario.Security.Exceptions.UserNotFoundException;
import com.vedruna.tfg.GestionHorario.webapp.dto.DateDTO;
import com.vedruna.tfg.GestionHorario.webapp.dto.UsuarioDTO;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.TipoUser;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.UsuarioTipoUser;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.HorarioRepository;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.TipoUserRepository;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioRepository;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioTipoUserRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUserRepository tipoUserRepository;

    @Autowired
    private UsuarioTipoUserRepository usuarioTipoUserRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {

        System.out.println(usuarioDTO.getNombre());
        System.out.println(usuarioDTO.getCorreo());
        System.out.println(usuarioDTO.getContrasena());

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setContraseña(passwordEncoder.encode(usuarioDTO.getContrasena()));

        Usuario respuesta = usuarioRepository.save(usuario);

        TipoUser tipoUser = new TipoUser();
        tipoUser.setId(2L);
        tipoUser.setTipo("USER");

        UsuarioTipoUser usuarioTipoUser = new UsuarioTipoUser();
        usuarioTipoUser.setUsuario(usuario);
        usuarioTipoUser.setTipoUser(tipoUser);

        usuarioTipoUserRepository.save(usuarioTipoUser);

        return respuesta;
    }

    public Usuario crearAdmin(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setContraseña(passwordEncoder.encode(usuarioDTO.getContrasena()));

        Usuario respuesta = usuarioRepository.save(usuario);

        TipoUser tipoUser = new TipoUser();
        tipoUser.setId(1L);
        tipoUser.setTipo("ADMIN");

        UsuarioTipoUser usuarioTipoUser = new UsuarioTipoUser();
        usuarioTipoUser.setUsuario(usuario);
        usuarioTipoUser.setTipoUser(tipoUser);

        usuarioTipoUserRepository.save(usuarioTipoUser);

        return respuesta;
    }

    public Usuario actualizarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findByCorreo(usuarioDTO.getCorreo());

        if(usuario == null){
            throw new UserNotFoundException();
        }

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setContraseña(passwordEncoder.encode(usuarioDTO.getContrasena()));

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminarUsuario(@NotNull LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByCorreo(loginDTO.getCorreo());

        if(usuario == null){
            throw new UserNotFoundException();
        }
        System.out.println(usuario.getId());

        usuarioTipoUserRepository.deleteById(usuarioTipoUserRepository.findByUsuarioId(usuario.getId()).getId());

        horarioRepository.deleteByUsuarioId(usuario.getId());

        usuarioRepository.deleteById(usuario.getId());

    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> listarUsuariosUser() {
        // Lógica para obtener usuarios cuyo tipo sea "USER"
        return usuarioRepository.findAllById(usuarioTipoUserRepository.findByTipoUser("USER"));
    }

    public List<Usuario> listarUsuariosAdmin() {
        // Lógica para obtener usuarios cuyo tipo sea "ADMIN"
        return usuarioRepository.findAllById(usuarioTipoUserRepository.findByTipoUser("ADMIN"));
    }

    public List<Usuario> getUsuariosSinFichar(DateDTO dateDTO) {

        LocalDate inicio = dateDTO.validarFecha(dateDTO.getMes(), dateDTO.getDiaInicio());
        LocalDate fin = dateDTO.validarFecha(dateDTO.getMes(), dateDTO.getDiaFin());

        return usuarioRepository.findUsersWithoutCheckInOneWeek(inicio, fin);
    }

}
