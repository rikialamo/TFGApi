package com.vedruna.tfg.GestionHorario.webapp.persistence.repository;

import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Horario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    Horario save(Horario entradaSalida);

    @Modifying
    @Query("DELETE FROM Horario h WHERE h.usuario.id = :id")
    void deleteByUsuarioId(@Param("id") long id);

}

