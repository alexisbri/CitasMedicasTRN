package com.alexis.pacientes.Service;

import com.alexis.commons.enums.EstadoRegistro;
import com.alexis.commons.exceptions.RecursoNoEncontradoException;
import com.alexis.commons.dto.paciente.PacienteRequest;
import com.alexis.commons.dto.paciente.PacienteResponse;
import com.alexis.pacientes.entity.Paciente;
import com.alexis.pacientes.mapper.PacienteMapper;
import com.alexis.pacientes.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Slf4j
@AllArgsConstructor
@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    private final PacienteMapper pacienteMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PacienteResponse> listar() {

        log.info("Listando todos los médicos activos");

        return pacienteRepository.findByEstadoRegistro(EstadoRegistro.ACTIVO).stream()
                .map(pacienteMapper::entidadAResponse).toList();
    }


    @Override
    public PacienteResponse obtenerPorId(Long id) {
        return pacienteMapper.entidadAResponse(obtenerPacienteActivoOException(id));
    }

    @Override
    public PacienteResponse obtenerPacientePorIdSinEstado(Long id) {

        log.info("Buscando paciente sin estado con id {} ", id);

        return pacienteMapper.entidadAResponse(pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Paciente activo no encontrado con id: " + id)));
    }


    @Override
    public PacienteResponse registrar(PacienteRequest request) {

        log.info("Registrando nuevo paciente.");

        validarDatosUnicos(request);

        Paciente paciente = pacienteMapper.requestAEntidad(request);

        pacienteRepository.save(paciente);

        log.info("Nuevo paciente registrado: {}", paciente.getNombre());

        return pacienteMapper.entidadAResponse(paciente);
    }


    @Override
    public PacienteResponse actualizar(PacienteRequest request, Long id) {

        Paciente paciente = obtenerPacienteActivoOException(id);

        validarCambiosUnicos(request, id);

        log.info("Actualizando datos de paciente.");

        paciente.actualizar(
                request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno(),
                request.edad(),
                request.peso(),
                request.estatura(),
                request.email(),
                request.telefono(),
                request.direccion()
        );

        log.info("Paciente actualizado con éxito: " + id + ".");

        return pacienteMapper.entidadAResponse(paciente);
    }




    @Override
    public void eliminar(Long id) {

        Paciente paciente = obtenerPacienteActivoOException(id);

        log.info("Eliminando paciente.");

        paciente.eliminar();

        log.info("Paciente con id " + id + " ha sido eliminado.");

    }





    /// METODOS PRIVADOS



    private Paciente obtenerPacienteActivoOException(Long id) {

        log.info("Buscando paciente activo con id {} ", id);

        return pacienteRepository.findByIdAndEstadoRegistro(id, EstadoRegistro.ACTIVO)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Paciente no encontrado con id: " + id));

    }

    public void validarDatosUnicos(PacienteRequest request) {

        log.info("Validando email único...");

        if (pacienteRepository.existsByEmailIgnoreCaseAndEstadoRegistro(
                request.email().trim(), EstadoRegistro.ACTIVO)) {

            throw new IllegalArgumentException("Ya existe un paciente activo registrado con el email: " +
                    request.email());
        }

        log.info("Validando teléfono único...");

        if (pacienteRepository.existsByTelefonoAndEstadoRegistro(
                request.telefono().trim(), EstadoRegistro.ACTIVO)) {

            throw new IllegalArgumentException("Ya existe un paciente activo registrado con el teléfono: " +
                    request.telefono());
        }

    }

    public void validarCambiosUnicos(PacienteRequest request, Long id) {

        log.info("Validando cambio en email único...");

        if (pacienteRepository.existsByEmailIgnoreCaseAndIdNotAndEstadoRegistro (
                request.email().trim(), EstadoRegistro.ACTIVO, id)) {

            throw new IllegalArgumentException("Ya existe un paciente activo registrado con el email: " +
                    request.email());

        }

        log.info("Validando cambio en teléfono único...");
        if (pacienteRepository.existsByTelefonoAndIdNotAndEstadoRegistro (

                request.telefono().trim(), EstadoRegistro.ACTIVO, id)) {

            throw new IllegalArgumentException("Ya existe un paciente activo registrado con el teléfono: " +
                    request.telefono());

        }

    }

}
