package com.lovechat.myselve.lovechat.logic.layers.database.interfaces

import android.content.Context
import com.lovechat.myselve.lovechat.logic.layers.database.data.User

interface UserManagerLocale {

   fun registerUser(user : User)
   fun unregisterUser() : Boolean
   fun checkUser(context: Context): Boolean

}