package com.vedruna.tfg.GestionHorario.webapp.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuarioId", referencedColumnName = "id")
    private Usuario usuario;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "tipoDatoId", referencedColumnName = "id")
    private TipoDato tipoDato;


}
