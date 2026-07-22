package com.alexis.medicos.repository;

import com.alexis.medicos.entity.Medico;
import com.alexis.medicos.enums.EstadoRegistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    List<Medico> findAllByEstadoRegistro(EstadoRegistro estadoRegistro);

    Optional<Medico> findByIdAndEstadoRegistro(Long id, EstadoRegistro estadoRegistro);

    boolean existsByEmailIgnoreCaseAndEstadoRegistro(String email, EstadoRegistro estadoRegistro);

    boolean existsByTelefonoAndEstadoRegistro(String telefono, EstadoRegistro estadoRegistro);

    boolean existsByCedulaProfesionalIgnoreCaseAndEstadoRegistro(String cedulaProfecional, EstadoRegistro estadoRegistro);

    boolean existsByEmailIgnoreCaseAndEstadoRegistroAndIdNot(String email, EstadoRegistro estadoRegistro, Long id);

    boolean existsByTelefonoAndEstadoRegistroAndIdNot(String telefono, EstadoRegistro estadoRegistro, Long id);

    boolean existsByCedulaProfesionalIgnoreCaseAndEstadoRegistroAndIdNot(String cedulaProfesional, EstadoRegistro estadoRegistro, Long id);

}
