package com.vedruna.tfg.GestionHorario.Security.Exceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(){super("Acceso no autorizado");}

}
