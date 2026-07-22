package com.alexis.citas.dto;

import com.alexis.commons.dto.medicos.DatosMedico;
import com.alexis.commons.dto.paciente.DatosPaciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CitaResponse(
        Long id,
        DatosPaciente paciente,
        DatosMedico medico,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fechaCita,
        String sintomas,
        String estadoCita
) {}
