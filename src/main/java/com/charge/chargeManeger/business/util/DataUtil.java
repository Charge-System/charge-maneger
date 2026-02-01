package com.charge.chargeManeger.business.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
* Objetivo: auxiliar na manipulação de data
* */
public class DataUtil {

    private static final SimpleDateFormat FORMATADOR_DATA_US = new SimpleDateFormat("yyyy-MM-dd");

    public static String converterDataString(Date date) {
        return FORMATADOR_DATA_US.format(date);
    }
}
