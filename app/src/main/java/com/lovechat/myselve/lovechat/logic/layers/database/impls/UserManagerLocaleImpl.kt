package com.lovechat.myselve.lovechat.logic.layers.database.impls

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.lovechat.myselve.lovechat.logic.layers.database.data.User
import com.lovechat.myselve.lovechat.logic.layers.database.interfaces.UserManager

class UserManagerLocaleImpl private constructor() : UserManager {


    override fun checkUser(context: Context?): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).all.isEmpty()
    }

    private lateinit var preferences: SharedPreferences

    override fun registerUser(user: User) {

    }

    override fun unregisterUser(): Boolean {
        return false
    }


    companion object {
        val instance =
            UserManagerLocaleImpl()
    }
}
