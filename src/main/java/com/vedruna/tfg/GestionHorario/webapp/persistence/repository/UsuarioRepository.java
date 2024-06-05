package com.vedruna.tfg.GestionHorario.webapp.persistence.repository;

import com.vedruna.tfg.GestionHorario.webapp.persistence.model.Usuario;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);

    Usuario findByNombre(String nombre);

    @Query("SELECT DISTINCT u FROM Usuario u WHERE NOT EXISTS (SELECT h FROM Horario h WHERE h.usuario = u AND DATE(h.fecha) BETWEEN :inicio AND :fin)")
    List<Usuario> findUsersWithoutCheckInOneWeek(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

}