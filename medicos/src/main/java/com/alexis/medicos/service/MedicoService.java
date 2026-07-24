package com.alexis.medicos.service;

import com.alexis.commons.dto.medicos.MedicoResponse;
import com.alexis.commons.service.CrudService;
import com.alexis.commons.dto.medicos.MedicoRequest;

public interface MedicoService extends CrudService<MedicoRequest, MedicoResponse> {

    MedicoResponse obtenerMedicoPorIdSinEstado(Long id);

    void actualizarDisponibilidadMedico(Long idMedico, Long idDisponibilidad);
}
