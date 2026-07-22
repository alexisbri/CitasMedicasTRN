package com.alexis.commons.clients;

import com.alexis.commons.dto.paciente.PacienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pacientes")
public interface PacienteClient {

    @GetMapping("/{id}")
    PacienteResponse obtenerPacienteActivoPorId(@PathVariable Long id);

    @GetMapping("/id-paciente/{id}")
    PacienteResponse obtenerPacienteSinEstadoPorId(@PathVariable Long id);
}
