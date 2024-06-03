package com.vedruna.tfg.GestionHorario.webapp.persistence.repository;

import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import com.vedruna.tfg.GestionHorario.webapp.persistence.model.UsuarioTipoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioTipoUserRepository  extends JpaRepository<UsuarioTipoUser, Long> {

    UsuarioTipoUser findByUsuarioId(Long id);

    @Query("SELECT utu.usuario.id FROM UsuarioTipoUser utu JOIN utu.tipoUser tu WHERE tu.tipo =:tipo")
    List<Long> findByTipoUser(@Param("tipo") String tipo);
}
