package com.lovechat.myselve.lovechat.logic.layers.registration.interfaces

import com.lovechat.myselve.lovechat.logic.layers.registration.data.User

interface UserManager {

   fun registerUser(user : User)
   fun unregisterUser() : Boolean
   fun checkUser(): Boolean

}