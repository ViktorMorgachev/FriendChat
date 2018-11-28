package com.lovechat.myselve.lovechat.logic.layers.database.registration.factory

import com.lovechat.myselve.lovechat.logic.layers.database.impls.UserManagerInternetImpl
import com.lovechat.myselve.lovechat.logic.layers.database.impls.UserManagerLocaleImpl
import com.lovechat.myselve.lovechat.logic.layers.database.interfaces.UserManagerInternet
import com.lovechat.myselve.lovechat.logic.layers.database.interfaces.UserManagerLocale

class FactoryInternetUserManager {

    companion object {
        fun Build() : UserManagerInternet {
             return UserManagerInternetImpl.instance
        }
    }

}