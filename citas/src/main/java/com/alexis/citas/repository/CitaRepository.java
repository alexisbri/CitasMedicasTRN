package com.alexis.citas.repository;

import com.alexis.citas.entity.Cita;
import com.alexis.commons.enums.EstadoRegistro;
import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByEstadoRegistro(EstadoRegistro estadoRegistro);

    Optional<Cita> findByIdAndEstadoRegistro(Long id, EstadoRegistro estadoRegistro);
}
