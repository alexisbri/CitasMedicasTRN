package com.alexis.medicos.controller;

import com.alexis.commons.controller.CommonController;
import com.alexis.commons.dto.medicos.MedicoRequest;
import com.alexis.commons.dto.medicos.MedicoResponse;
import com.alexis.medicos.service.MedicoService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class MedicoController extends CommonController<MedicoRequest, MedicoResponse, MedicoService> {
    public MedicoController(MedicoService service) {
        super(service);
    }

    @GetMapping("/id-medico/{id}")
    public ResponseEntity<MedicoResponse> obtenerMedicoPorIdSinEstado(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id) {
        return ResponseEntity.ok(service.obtenerMedicoPorIdSinEstado(id));
    }

    @PutMapping("/{idMedico}/disponibilidad/{idDisponibilidad}")
    public ResponseEntity<Void> actualizarDisponibilidadMedico(
            @PathVariable @Positive(message = "El idMedico debe ser positivo") Long idMedico,
            @PathVariable @Positive(message = "El idDisponibilidad debe ser positivo") Long idDisponibilidad) {
        service.actualizarDisponibilidadMedico(idMedico, idDisponibilidad);
        return ResponseEntity.noContent().build();
    }
}
