package com.vedruna.tfg.GestionHorario.webapp.persistence.repository;

import com.vedruna.tfg.GestionHorario.webapp.persistence.model.TipoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUserRepository extends JpaRepository<TipoUser, Long> {
    TipoUser findByTipo(String tipo);
}