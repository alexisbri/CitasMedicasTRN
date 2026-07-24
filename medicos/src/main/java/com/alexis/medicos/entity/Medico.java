package com.alexis.medicos.entity;

import com.alexis.commons.utils.StringCustomUtils;
import com.alexis.commons.utils.ValoresNumericosUtils;
import com.alexis.medicos.enums.DisponibilidadMedico;
import com.alexis.medicos.enums.EspecialidadMedico;
import com.alexis.medicos.enums.EstadoRegistro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "MEDICOS")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MEDICO")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", nullable = false, length = 50)
    private String apellidoMaterno;

    @Column(name = "EDAD")
    private Short edad;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "CEDULA_PROFESIONAL", nullable = false, length = 12)
    private String cedulaProfesional;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESPECIALIDAD", nullable = false)
    private EspecialidadMedico especialidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "DISPONIBILIDAD", nullable = false)
    private DisponibilidadMedico disponibilidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO_REGISTRO", nullable = false, length = 30)
    private EstadoRegistro estadoRegistro;


    public void actualizar(
            String nombre, String apellidoPaterno, String apellidoMaterno, Short edad, String email,
            String telefono, String cedulaProfesional, EspecialidadMedico especialidad) {

        validarNoEliminado();

        validarDatos(
                nombre, apellidoPaterno, apellidoMaterno,
                edad, email, telefono, cedulaProfesional);

        actualizarEspecialidad(especialidad);

        this.nombre = nombre.trim();
        this.apellidoPaterno = apellidoPaterno.trim();
        this.apellidoMaterno = apellidoMaterno.trim();
        this.edad = edad;
        this.email = email.trim().toLowerCase();
        this.telefono = telefono.trim();
        this.cedulaProfesional = cedulaProfesional.trim();
    }



    private void validarNoEliminado() {
        if (this.estadoRegistro == EstadoRegistro.ELIMINADO)
            throw new IllegalStateException("El médico ya está eliminado");
    }

    public void actualizarEspecialidad(EspecialidadMedico nuevaEspecialidad) {
        validarNoEliminado();

        if (nuevaEspecialidad == null)
            throw new IllegalStateException("La disponibilidad es requerida");

        this.especialidad = nuevaEspecialidad;
    }

    public void actualizarDisponibilidad(DisponibilidadMedico nuevaDisponibilidad) {

        validarNoEliminado();

        if (nuevaDisponibilidad == null)
            throw new IllegalArgumentException("La disponibiidad es requerida");

    }

    public void eliminar() {

        validarNoEliminado();

        this.estadoRegistro = EstadoRegistro.ELIMINADO;

    }


    private void validarDatos (String nombre, String apellidoPaterno,
                               String apellidoMaterno, Short edad, String email,
                               String telefono, String cedulaProfesional
                               ) {

            StringCustomUtils. validarTamanio(nombre, 1, 50,
        "El nombre es requerido.");

            StringCustomUtils.validarTamanio(apellidoPaterno, 1, 50,
            "El apellido paterno es requerido.");

            StringCustomUtils.validarTamanio(apellidoMaterno, 1, 50,
            "El apellido materno es requerido.");

            StringCustomUtils.validarTamanio(email, 1, 50,
            "El email es requerido.");

            StringCustomUtils.validarTamanio(telefono, 1, 50,
            "El telefono es requerido.");

            StringCustomUtils.validarTamanio(cedulaProfesional, 12, 12, "La cedula profeciona es requerida.");

            ValoresNumericosUtils.validarRangoShort(edad, (short) 18, (short) 100, "La edad es requerida.");

            if (especialidad == null)
                throw new IllegalArgumentException("La especialidad es requerida");

    }


}
