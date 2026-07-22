package com.alexis.pacientes.Service;

import com.alexis.commons.service.CrudService;
import com.alexis.pacientes.dto.PacienteRequest;
import com.alexis.pacientes.dto.PacienteResponse;
import com.alexis.pacientes.repository.PacienteRepository;

public interface PacienteService extends CrudService<PacienteRequest, PacienteResponse> {

    PacienteResponse obtenerPacientePorIdSinEstado(Long id);

}
