package com.fincatto.documentofiscal.validadores;

import java.math.BigInteger;

public abstract class DFBigIntegerValidador {

    public static void tamanho12(final BigInteger valor, final String info) {
        if (valor != null && valor.compareTo(new BigInteger("999999999999")) > 0) {
            throw new NumberFormatException(String.format("%s com tamanho maior que 12", info));
        }
    }

    public static void tamanho11(final BigInteger valor, final String info) {
        if (valor != null && valor.compareTo(new BigInteger("99999999999")) > 0) {
            throw new NumberFormatException(String.format("%s com tamanho maior que 11", info));
        }
    }
    
    public static void tamanho12(final String valor, final String info) {
        if (valor != null && valor.length() > 12) {
            throw new NumberFormatException(String.format("%s com tamanho maior que 12", info));
        }
    }

    public static void tamanho11(final String valor, final String info) {
        if (valor != null && valor.length() > 11) {
            throw new NumberFormatException(String.format("%s com tamanho maior que 11", info));
        }
    }
}
