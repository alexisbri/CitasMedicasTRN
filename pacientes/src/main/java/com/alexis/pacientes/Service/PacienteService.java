package com.alexis.pacientes.Service;

import com.alexis.commons.service.CrudService;
import com.alexis.commons.dto.paciente.PacienteRequest;
import com.alexis.commons.dto.paciente.PacienteResponse;

import java.util.List;

public interface PacienteService extends CrudService<PacienteRequest, PacienteResponse> {


    List<PacienteResponse> listar();

    PacienteResponse obtenerPorId(Long id);

    PacienteResponse obtenerPacientePorIdSinEstado(Long id);

    PacienteResponse registrar(PacienteRequest request);

    PacienteResponse actualizar(PacienteRequest equest, Long id);

    void eliminar(Long id);
}
