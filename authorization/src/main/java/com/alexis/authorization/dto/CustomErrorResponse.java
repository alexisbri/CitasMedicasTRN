package com.alexis.authorization.dto;

public record CustomErrorResponse(
        int codigo,
        String mensaje
) { }

