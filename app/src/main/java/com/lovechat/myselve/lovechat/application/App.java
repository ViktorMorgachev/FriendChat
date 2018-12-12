package com.lovechat.myselve.lovechat.application;

import android.app.Application;

import com.lovechat.myselve.lovechat.data.layers.database.interfaces.MessageService;
import com.lovechat.myselve.lovechat.data.layers.database.interfaces.UserRegistration;
import com.lovechat.myselve.lovechat.logic.communication.interfaces.MessageStorage;

public class App extends Application {

    MessageService mMessageService;
    MessageStorage mMessageStorage;
    UserRegistration mUserRegistration;

    public MessageService getMessageService() {
        return mMessageService;
    }

    public MessageStorage getMessageStorage() {
        return mMessageStorage;
    }

    public UserRegistration getUserRegistration() {
        return mUserRegistration;
    }
}
