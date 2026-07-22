package com.alexis.citas.enums;

import com.alexis.commons.exceptions.RecursoNoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@Getter
public enum EstadoCita {

    PENDIENTE(1L, "pendiente de confirmar", true, true) {
        @Override
        public Set<EstadoCita> puedeCambiar() {
            return Set.of();
        }
    },
    CONFIRMADA(2L, "Confirmada por el paciente", true, false) {
        @Override
        public Set<EstadoCita> puedeCambiar() {
            return EnumSet.of(CONFIRMADA,CANCELADA);
        }
    },
    EN_CURSO(3L, "Paciente llegó a su cita", false, false) {
        @Override
        public Set<EstadoCita> puedeCambiar() {
            return EnumSet.of(CONFIRMADA,CANCELADA);
        }
    },
    FINALIZADA(4L, "Cita finalizada", false, true) {
        @Override
        public Set<EstadoCita> puedeCambiar() {
            return Set.of();
        }
    },
    CANCELADA(5L, "Cita cancelada", false, true) {
        @Override
        public Set<EstadoCita> puedeCambiar() {
            return Set.of();
        }
    };

    private final Long codigo;
    private final String descripcion;
    private final boolean actualizable;
    private final boolean eliminable;

    public abstract Set<EstadoCita> puedeCambiar();

    public boolean puedeCambiarA(EstadoCita nuevoEstado) {
        return this.puedeCambiar().contains(nuevoEstado);
    }

    public static EstadoCita obtenerEstadoCitaPorCodigo(Long codigo) {
        for (EstadoCita e : values()) {
            if (Objects.equals(e.codigo, codigo)) {
                return e;
            }
        }
        throw new RecursoNoEncontradoException("Código de cita no válido: " + codigo);
    }
}
