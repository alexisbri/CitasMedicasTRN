package com.alexis.commons.dto.medicos;


import jakarta.validation.constraints.*;

public record MedicoRequest(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
        String nombre,

        @NotBlank(message = "El apellido paterno es obligatorio")
        @Size(min = 1, max = 50, message = "El apellido paterno debe tener entre 1 y 50 caracteres")
        String apellidoPaterno,

        @NotBlank(message = "El apellido materno es obligatorio")
        @Size(min = 1, max = 50, message = "El apellido materno debe tener entre 1 y 50 caracteres")
        String apellidoMaterno,

        @NotNull(message = "La edad es obligatoria")
        @Min(value = 18, message = "La edad mínima es 18 años")
        @Max(value = 100, message = "La edad máxima es 100 años")
        Short edad,

        @NotBlank(message = "El email es obligatorio")
        @Size(min = 1, max = 100, message = "El email debe tener entre 1 y 100 caracteres")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe tener exactamente 10 dígitos numéricos")
        String telefono,

        @NotBlank(message = "La cédula profesional es obligatoria")
        @Size(min = 12, max = 12, message = "La cédula profesional debe tener exactamente 12 caracteres")
        String cedulaProfesional,

        @NotNull(message = "El id de la especialidad es requerido")
        @Positive(message = "El id de la especialidad debe ser positivo")
        Long idEspecialidad

) { }