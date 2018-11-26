package com.lovechat.myselve.lovechat.logic.layers.interactors;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Developer on 14.04.2018.
 */

public interface KeysCommonInteractor {

    @StringDef({ KeysField.LOG_TAG, KeysField.PASSWORD_KEY, KeysField.USER_KEY})
    @Retention(RetentionPolicy.SOURCE)
    @interface KeysField {

        String LOG_TAG = "LoveChat_LOG";
        String PASSWORD_KEY = "SPref_Password";
        String USER_KEY = "SPref_User";
    }
}
