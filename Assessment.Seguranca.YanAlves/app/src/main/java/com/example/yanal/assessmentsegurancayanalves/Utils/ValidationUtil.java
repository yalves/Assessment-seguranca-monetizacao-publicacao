package com.example.yanal.assessmentsegurancayanalves.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yanal on 06/10/2017.
 */

public class ValidationUtil {
    public static boolean TemCaracteresEspeciais(String string)
    {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        return m.find();
    }

    public static boolean EhEmailValido(String email) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        return m.find();
    }

    public static boolean EhCpfValido(String email) {
        Pattern p = Pattern.compile("^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        return m.find();
    }
}
