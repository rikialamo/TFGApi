package com.vedruna.tfg.GestionHorario.Security.LoginController;

import com.vedruna.tfg.GestionHorario.Security.Exceptions.IncorrectDateException;
import com.vedruna.tfg.GestionHorario.Security.Exceptions.IncorrectPasswordException;
import com.vedruna.tfg.GestionHorario.Security.Exceptions.UnauthorizedException;
import com.vedruna.tfg.GestionHorario.Security.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectPasswordException(Exception e) {
        return "IncorrectPassword";
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUnauthorizedException(Exception e) {
        return "Acceso no autorizado";
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserNotFoundException(Exception e) {
        return "Usuario no encontrado";
    }

    @ExceptionHandler(IncorrectDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIncorrectDateException(Exception e) {
        return "Fecha incorrecta";
    }

}
