package com.vedruna.tfg.GestionHorario.Security.Exceptions;

public class IncorrectPasswordException extends RuntimeException{

    public IncorrectPasswordException(){super("contraseña incorrecta");}

}
