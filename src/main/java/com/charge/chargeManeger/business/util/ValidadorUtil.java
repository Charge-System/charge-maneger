package com.charge.chargeManeger.business.util;

import java.util.regex.Pattern;

/*
* Objetivo: Conter métodos de validação
* geral
* */
public class ValidadorUtil {

    private static final String REGEX_EMAIL_VALIDAR = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    // Metodo que valida um email qualquer
    public static boolean validarEmail(String email) {
        return Pattern.compile(REGEX_EMAIL_VALIDAR).matcher(email).matches();
    }
}
