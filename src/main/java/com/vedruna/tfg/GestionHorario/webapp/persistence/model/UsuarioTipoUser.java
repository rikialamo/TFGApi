package com.vedruna.tfg.GestionHorario.webapp.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario_tipo_user")
public class UsuarioTipoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_tipo_user_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_user_id", referencedColumnName = "id")
    private TipoUser tipoUser;

}

