package com.vedruna.tfg.GestionHorario.webapp.dto;

import com.vedruna.tfg.GestionHorario.Security.Exceptions.IncorrectPasswordException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DateDTO {

    int mes;

    int diaInicio;

    int diaFin;

    public LocalDate validarFecha(int mes, int dia) {
        try {
            // Intenta crear una fecha con los valores proporcionados
            LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), mes, dia);
            return localDate; // La fecha es válida
        } catch (java.time.DateTimeException e) {
            throw new IncorrectPasswordException();
        }
    }

}
