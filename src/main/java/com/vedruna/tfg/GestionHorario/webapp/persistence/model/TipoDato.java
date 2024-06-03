package com.vedruna.tfg.GestionHorario.webapp.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipoDato")
public class TipoDato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
