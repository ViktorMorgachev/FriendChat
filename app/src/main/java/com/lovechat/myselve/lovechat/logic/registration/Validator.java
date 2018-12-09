package com.lovechat.myselve.lovechat.logic.registration;

import android.util.Log;

import com.lovechat.myselve.lovechat.logic.interactors.KeysCommonInteractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class: Validator
 * Author: RAM
 * Description: Проверка строк на совместимость через регулярные выражения;
 */
public class Validator {
    public static final String PASSWORD_PATTERN = "^[A-Z0-9]{9,15}$";
    public static final String PHONE_PATTERN = "^[+][0-9]{9,15}$";
    public static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}$";
    public static final String URL_PATTERN = "/^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$/";

    public static boolean valid(String pattern, String validateString) {
        Log.d(KeysCommonInteractor.KeysField.LOG_TAG, Validator.class.getCanonicalName() + " valid()");
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(validateString);
        return m.matches();
    }
}
