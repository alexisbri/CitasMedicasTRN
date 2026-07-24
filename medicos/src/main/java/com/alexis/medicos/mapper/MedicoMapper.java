package com.alexis.medicos.mapper;


import com.alexis.commons.dto.medicos.MedicoRequest;
import com.alexis.commons.dto.medicos.MedicoResponse;
import com.alexis.commons.mapper.CommonMapper;
import com.alexis.medicos.entity.Medico;
import com.alexis.medicos.enums.DisponibilidadMedico;
import com.alexis.medicos.enums.EstadoRegistro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicoMapper implements CommonMapper<MedicoRequest, MedicoResponse, Medico> {

    public Medico requestAEntidad(MedicoRequest request) {
        if (request == null) return null;

        return Medico.builder()
                .nombre(request.nombre().trim())
                .apellidoPaterno(request.apellidoPaterno().trim())
                .apellidoMaterno(request.apellidoMaterno().trim())
                .edad(request.edad())
                .email(request.email().trim())
                .telefono(request.telefono().trim())
                .cedulaProfesional(request.cedulaProfesional().trim())
                .disponibilidad(DisponibilidadMedico.DISPONIBLE)
                .estadoRegistro(EstadoRegistro.ACTIVO)
                .build();
    }

    @Override
    public MedicoResponse entidadAResponse(Medico entidad) {
        if(entidad == null) return null;

        return new MedicoResponse(
                entidad.getId(),
                String.join(" ",
                        entidad.getNombre(),
                        entidad.getApellidoPaterno(),
                        entidad.getApellidoMaterno()),
                entidad.getEdad(),
                entidad.getEmail(),
                entidad.getTelefono(),
                entidad.getCedulaProfesional(),
                entidad.getEspecialidad().getDescripcion(),
                entidad.getDisponibilidad().getDescripcion(),
                entidad.getDisponibilidad().getCodigo()
        );
    }









}
