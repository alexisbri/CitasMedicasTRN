package com.alexis.pacientes.Service;

import com.alexis.pacientes.dto.PacienteRequest;
import com.alexis.pacientes.dto.PacienteResponse;
import com.alexis.pacientes.mapper.PacienteMapper;
import com.alexis.pacientes.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Slf4j
@AllArgsConstructor
@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    private final PacienteMapper pacienteMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PacienteResponse> listar() {
        return List.of();
    }

    @Override
    public PacienteResponse obtenerPorId(Long id) {
        return null;
    }

    @Override
    public PacienteResponse obtenerPacientePorIdSinEstado(Long id) {
        return null;
    }

    @Override
    public PacienteResponse registrar(PacienteRequest request) {
        return null;
    }

    @Override
    public PacienteResponse actualizar(PacienteRequest request, Long id) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }

}
