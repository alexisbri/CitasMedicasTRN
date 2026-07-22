package com.alexis.citas.service;

import com.alexis.citas.dto.CitaRequest;
import com.alexis.citas.dto.CitaResponse;
import com.alexis.commons.service.CrudService;

public interface CitaService extends CrudService<CitaRequest, CitaResponse> {

    void actualizarEstadoCita(Long idCita, Long idEstadoCita);

}
