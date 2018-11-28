package com.lovechat.myselve.lovechat.logic.layers.database.impls

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.lovechat.myselve.lovechat.logic.layers.database.data.User
import com.lovechat.myselve.lovechat.logic.layers.database.interfaces.UserManager
import com.lovechat.myselve.lovechat.logic.layers.interactors.KeysCommonInteractor

class UserManagerInternetImpl private constructor() : UserManager {

    lateinit var mAuth: FirebaseAuth

    override fun registerUser(user: User) {
        mAuth = FirebaseAuth.getInstance()


        mAuth.addAuthStateListener {
            if(it.currentUser != null)
                Log.d(KeysCommonInteractor.KeysField.LOG_TAG, "${javaClass.canonicalName} registerUser(user: User) successful")
            else
                Log.d(KeysCommonInteractor.KeysField.LOG_TAG, "${javaClass.canonicalName} registerUser(user: User) error")
        }

        mAuth.createUserWithEmailAndPassword(user.email.toString(), user.password.toString())
            .addOnCompleteListener {
                when{
                  //  it.isSuccessful -> // Нужно подписаться на событие и в случае успеха или не успеха указать
                    // Активности какой результат получили
                }
            }

    }

    override fun unregisterUser(): Boolean {
        return false
    }

    override fun checkUser(context: Context?): Boolean {
        mAuth = FirebaseAuth.getInstance()
        return !mAuth.currentUser!!.equals("")
    }

    companion object {
        val instance =
            UserManagerInternetImpl()
    }
}
