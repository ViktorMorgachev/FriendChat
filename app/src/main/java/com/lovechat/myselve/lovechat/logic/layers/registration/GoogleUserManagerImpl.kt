package com.lovechat.myselve.lovechat.logic.layers.registration

import com.lovechat.myselve.lovechat.logic.layers.registration.data.User
import com.lovechat.myselve.lovechat.logic.layers.registration.interfaces.UserManager


class GoogleUserManagerImpl : UserManager {

    override fun unregisterUser(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkUser(): Boolean {
       return false
    }

    override fun registerUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}