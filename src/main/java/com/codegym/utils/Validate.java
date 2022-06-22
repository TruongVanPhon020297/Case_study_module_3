package com.codegym.utils;

import java.util.regex.Pattern;

public class Validate {
    public static final String NUMBER_REGEX = "\\d+";

    public static boolean isNumberValid(String number) {
        return Pattern.compile(NUMBER_REGEX).matcher(number).matches();
    }
}
