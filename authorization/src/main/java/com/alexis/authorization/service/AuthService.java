package com.alexis.authorization.service;

import com.alexis.authorization.dto.LoginRequest;
import com.alexis.authorization.dto.TokenResponse;

public interface AuthService {

    TokenResponse autenticar(LoginRequest request) throws Exception;
}
