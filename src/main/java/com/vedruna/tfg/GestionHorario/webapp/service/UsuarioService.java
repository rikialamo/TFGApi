package com.vedruna.tfg.GestionHorario.webapp.service;

import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Horario;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.TipoDato;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.HorarioRepository;
import com.vedruna.tfg.GestionHorario.webapp.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HorarioRepository horarioRepository;

    public void registrarEntrada(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Captura la hora actual
        LocalDateTime horaActual = LocalDateTime.now();

        // Crea una nueva entrada en la base de datos con la hora actual
        Horario horarioEntrada = new Horario();
        horarioEntrada.setUsuario(usuario);
        TipoDato entrada = new TipoDato();
        entrada.setId(1L);
        horarioEntrada.setTipoDato(entrada);
        horarioEntrada.setFecha(horaActual);

        // Guarda la entrada en la base de datos
        horarioRepository.save(horarioEntrada);
    }

    public void registrarSalida(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Captura la hora actual
        LocalDateTime horaActual = LocalDateTime.now();

        // Crea una nueva salida en la base de datos con la hora actual
        Horario horarioSalida = new Horario();
        horarioSalida.setUsuario(usuario);
        TipoDato salida = new TipoDato();
        salida.setId(2L);
        horarioSalida.setTipoDato(salida);
        horarioSalida.setFecha(horaActual);

        // Guarda la salida en la base de datos
        horarioRepository.save(horarioSalida);
    }
}

