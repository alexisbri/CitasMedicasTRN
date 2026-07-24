package com.alexis.authorization.service;

import com.alexis.authorization.dto.UsuarioRequest;
import com.alexis.authorization.dto.UsuarioResponse;

import java.util.Set;

public interface UsuarioService {

    Set<UsuarioResponse> listar();

    UsuarioResponse registrar(UsuarioRequest request);

    UsuarioResponse eliminar(String username);
}
