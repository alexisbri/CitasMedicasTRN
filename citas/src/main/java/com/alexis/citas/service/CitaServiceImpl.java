package com.alexis.citas.service;

import com.alexis.citas.repository.CitaRepository;
import com.alexis.citas.dto.CitaRequest;
import com.alexis.citas.dto.CitaResponse;
import com.alexis.citas.entity.Cita;
import com.alexis.citas.enums.EstadoCita;
import com.alexis.citas.mapper.CitaMapper;
import com.alexis.commons.clients.MedicoClient;
import com.alexis.commons.clients.PacienteClient;
import com.alexis.commons.dto.medicos.MedicoResponse;
import com.alexis.commons.dto.paciente.PacienteResponse;
import com.alexis.commons.enums.EstadoRegistro;
import com.alexis.commons.exceptions.RecursoNoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    private final CitaMapper citaMapper;

    private final MedicoClient medicoClient;

    private final PacienteClient pacienteClient;

    @Override
    public List<CitaResponse> listar() {

        log.info("Listandndo todas las citas activas");

        return citaRepository.findByEstadoRegistro(EstadoRegistro.ACTIVO).stream()
                .map(cita -> citaMapper.entidadAResponse(
                        cita,
                        obtenerPacienteSinEstado(cita.getIdPaciente()),
                        obtenerMedicoSinEstado(cita.getIdMedico())
                )).toList();
    }


    @Override
    @Transactional(readOnly = true)
    public CitaResponse obtenerPorId(Long id) {

        Cita cita = obtenerCitaOException(id);

        return citaMapper.entidadAResponse(
                cita,
                obtenerPacienteSinEstado(cita.getIdPaciente()),
                obtenerMedicoSinEstado(cita.getIdMedico())
        );
    }


    @Override
    public CitaResponse registrar(CitaRequest request) {
        log.info("Registrando nueva cita: {}", request);
        Cita cita = citaMapper.requestAEntidad(request);
        citaRepository.save(cita);

        log.info("Cita registrada exitósamente");
        return citaMapper.entidadAResponse(
                cita,
                obtenerPacienteSinEstado(cita.getIdPaciente()),
                obtenerMedicoSinEstado(cita.getIdMedico())
        );
    }


    @Override
    public CitaResponse actualizar(CitaRequest request, Long id) {

        Cita cita = obtenerCitaOException(id);

        log.info("Actualizando cita con id: {}", id);

        cita.actualizar(
                request.idPaciente(),
                request.idMedico(),
                request.fechaCita(),
                request.sintomas()
        );

        log.info("Cita actualizada con id: {}", id);

        return citaMapper.entidadAResponse(
                cita,
                obtenerPacienteSinEstado(cita.getIdPaciente()),
                obtenerMedicoSinEstado(cita.getIdMedico())
        );
    }


    @Override
    public void eliminar(Long id) {
        Cita cita = obtenerCitaOException(id);
        log.info("Eliminando cita con id: {}", id);
        cita.eliminar();
        log.info("Cita con id {} ha sido marcada como eliminada", id);
    }


    @Override
    public void actualizarEstadoCita(Long idCita, Long idEstadoCita) {

        Cita cita = obtenerCitaOException(idCita);

        log.info("Actualizando estado de la cita con id: {}", idCita);

        cita.actualizarEstadoCita(EstadoCita.obtenerEstadoCitaPorCodigo(idEstadoCita));

        log.info("Estado de la cita {} actualizado correctamente", idCita);
    }



    /// METODOS PRIVADOS



    private Cita obtenerCitaOException(Long id) {

        log.info("Buscando cita con id: {}", id);

        return citaRepository.findById(id).orElseThrow(() ->
            new RecursoNoEncontradoException("Cita no encontrada con id: " + id)
        );
    }


    private MedicoResponse obtenerActivo(Long id) {
        log.info("Buscando médico activo con id {} en el servicio remoto...", id);
        return medicoClient.obtenerMedicoActivoPorId(id);
    }

    private MedicoResponse obtenerMedicoSinEstado(Long id) {
        log.info("Buscando médico sin estado con id {} en el servicio remoto...", id);
        return medicoClient.obtenerMedicoSinEstadoPorId(id);
    }


    private PacienteResponse obtenerPacienteActivo(Long id) {
        log.info("Buscando paciente activo con id {} en el servicio remoto...", id);
        return pacienteClient.obtenerPacienteActivoPorId(id);
    }

    private PacienteResponse obtenerPacienteSinEstado(Long id) {
        log.info("Buscando paciente sin estado con id {} en el servicio remoto...", id);
        return pacienteClient.obtenerPacienteSinEstadoPorId(id);
    }

}
