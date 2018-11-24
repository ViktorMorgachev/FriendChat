package com.lovechat.myselve.lovechat.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.lovechat.myselve.lovechat.R
import com.lovechat.myselve.lovechat.logic.layers.registration.factory.FactoryUserManager
import com.lovechat.myselve.lovechat.logic.layers.registration.interfaces.UserManager
import com.lovechat.myselve.lovechat.ui.fragments.ChatsFragment
import com.lovechat.myselve.lovechat.ui.fragments.RegisterFragment
import java.security.AccessController.getContext

class StartActivity : AppCompatActivity() {


    lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_fragment_container)

        when(FactoryUserManager.Build("loveChat")!!.checkUser()){
            false -> showUserRegister(savedInstanceState)
        }


    }

    private fun showUserRegister(savedInstanceState: Bundle?) {
        // If first starting activity
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .add(R.id.main_fragment_container, RegisterFragment())
                .commit()
        } else
        {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, RegisterFragment())
                .commit()
        }
    }
}

