package com.alexis.commons.dto.paciente;

public record DatosPaciente(
        String nombre,
        String numExpediente,
        String edad,
        String peso,
        String estatura,
        String imc,
        String telefono
) {}
