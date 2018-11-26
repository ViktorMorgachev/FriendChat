package com.lovechat.myselve.lovechat.logic.layers.database.registration.factory

import com.lovechat.myselve.lovechat.logic.layers.database.impls.UserManagerLocaleImpl
import com.lovechat.myselve.lovechat.logic.layers.database.interfaces.UserManagerLocale

class FactoryLocaleUserManager {

    companion object {
        fun Build() : UserManagerLocale {
             return UserManagerLocaleImpl.instance
        }
    }

}