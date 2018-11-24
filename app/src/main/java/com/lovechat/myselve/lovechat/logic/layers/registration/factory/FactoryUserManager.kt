package com.lovechat.myselve.lovechat.logic.layers.registration.factory

import com.lovechat.myselve.lovechat.logic.layers.registration.CustomUserManagerImpl
import com.lovechat.myselve.lovechat.logic.layers.registration.GoogleUserManagerImpl
import com.lovechat.myselve.lovechat.logic.layers.registration.interfaces.UserManager

class FactoryUserManager {

    companion object {
        fun Build(name: CharSequence) : UserManager? {
            var testString = name.toString().toLowerCase().trim()
            when{
                testString.equals("google") ->
                    return GoogleUserManagerImpl()
                testString.equals("loveChat", true) ->
                    return  CustomUserManagerImpl()
                else -> return null
            }
        }
    }

}