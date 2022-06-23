package com.codegym.utils;

import java.util.regex.Pattern;

public class Validate {
    public static final String NUMBER_REGEX = "\\d+";
    public static final String PASSWORD_REGEX = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^*])(?!.*['\"`]).{6,}";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    public static final String PHONE_REGEX = "^([+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*){10}$";

    public static boolean isPasswordValid(String password) {
        return Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }
    public static boolean isNumberValid(String number) {
        return Pattern.compile(NUMBER_REGEX).matcher(number).matches();
    }
    public static boolean isEmail(String email){
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
    public static boolean isPhone(String phone){
        return Pattern.compile(PHONE_REGEX).matcher(phone).matches();
    }
}
