package com.alexis.pacientes.repository;

import com.alexis.commons.enums.EstadoRegistro;
import com.alexis.pacientes.entity.Paciente;
import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    List<Paciente> findByEstadoRegistro(EstadoRegistro estadoRegistro);

    Optional<Paciente> findByIdAndEstadoRegistro(Long id, EstadoRegistro estadoRegistro);

    boolean existsByEmailIgnoreCaseAndEstadoRegistro(String email, EstadoRegistro estadoRegistro);

    boolean existsByEmailIgnoreCaseAndIdNotAndEstadoRegistro(String email, Long id, EstadoRegistro estadoRegistro);

    boolean existsByTelefonoAndEstadoRegistro(String telefono, EstadoRegistro estadoRegistro);

    boolean existsByTelefonoAndIdNotAndEstadoRegistro(String telefono, Long id, EstadoRegistro estadoRegistro);

}
