package com.alexis.pacientes.mapper;

import com.alexis.commons.enums.EstadoRegistro;
import com.alexis.commons.mapper.CommonMapper;
import com.alexis.pacientes.dto.PacienteRequest;
import com.alexis.pacientes.dto.PacienteResponse;
import com.alexis.pacientes.entity.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper implements CommonMapper<PacienteRequest, PacienteResponse, Paciente> {

    @Override
    public Paciente requestAEntidad(PacienteRequest request) {
        if (request == null) return null;

        return Paciente.builder()
                .nombre(request.nombre().trim())
                .apellidoPaterno(request.apellidoPaterno().trim())
                .apellidoMaterno(request.apellidoMaterno().trim())
                .email(request.email().toLowerCase().trim())
                .edad(request.edad())
                .estatura(request.estatura())
                .peso(request.peso())
                .telefono(request.telefono().trim())
                .direccion(request.direccion().trim())
                .estadoRegistro(EstadoRegistro.ACTIVO)
                .build();
    }

    @Override
    public PacienteResponse entidadAResponse(Paciente entidad) {
        if(entidad == null) return null;

        return new PacienteResponse(
                entidad.getId(),
                String.join(" ",
                        entidad.getNombre(),
                        entidad.getApellidoPaterno(),
                        entidad.getApellidoMaterno()
                ),
                entidad.getEdad(),
                entidad.getPeso(),
                entidad.getEstatura(),
                entidad.getImc(),
                entidad.getEmail(),
                entidad.getTelefono(),
                entidad.getDireccion(),
                entidad.getNumExpediente()
        );
    }
}
