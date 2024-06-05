package com.vedruna.tfg.GestionHorario.webapp.dto;

import com.vedruna.tfg.GestionHorario.Security.Exceptions.IncorrectDateException;
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
            return LocalDate.of(LocalDate.now().getYear(), mes, dia);
        } catch (java.time.DateTimeException e) {
            throw new IncorrectDateException();
        }
    }

}
