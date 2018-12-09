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
    public static final String PASSWORD_PATTERN = "[\\w]{9,15}$";
    public static final String PHONE_PATTERN = "^[+][0-9]{9,15}$";
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String URL_PATTERN = "/^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$/";

    public static boolean valid(String pattern, String validateString) {

        Log.d(KeysCommonInteractor.KeysField.LOG_TAG, Validator.class.getCanonicalName() + " valid()");
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(validateString);
        return m.matches();
    }
}
