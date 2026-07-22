package com.alexis.medicos.utils;

import java.math.BigDecimal;

public class ValoresNumericosUtils {

    public static <N extends Number> void validarNumeroRequerido(N numero) {
        if (numero == null)
            throw new IllegalArgumentException("El valor numérico es requerido");
    }

    public static void validarEnteroPositivo(Integer entero, String mensaje) {
        validarNumeroRequerido(entero);

        if (entero < 0)
            throw new IllegalArgumentException(mensaje);
    }

    public static void validarBigDecimalPositivo(BigDecimal numero, String mensaje) {
        validarNumeroRequerido(numero);

        if (numero.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException(mensaje);
    }



    public static void validatorBigDecimalPositive(BigDecimal numero, String mensaje) {  // no usages
        validarNumeroRequerido(numero);

        if (numero.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException(mensaje);
    }

    public static void validarRangoShort(Short numero, short min, short max, String mensaje) {  // no usages
        validarNumeroRequerido(numero);

        if (numero < min || numero > max)
            throw new IllegalArgumentException(mensaje);
    }


    // validatorNumeroRequerido

    public static void validatorRangoDouble(Double numero, double min, double max, String mensaje) {  // no usages
        validarNumeroRequerido(numero);

        if (numero < min || numero > max)
            throw new IllegalArgumentException(mensaje);
    }



}
