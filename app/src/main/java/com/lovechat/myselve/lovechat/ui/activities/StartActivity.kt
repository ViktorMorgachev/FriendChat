package com.lovechat.myselve.lovechat.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lovechat.myselve.lovechat.R
import com.lovechat.myselve.lovechat.models.data.User
import com.lovechat.myselve.lovechat.data.layers.database.impls.UserManagerInternetImpl
import com.lovechat.myselve.lovechat.data.layers.database.impls.UserManagerLocaleImpl
import com.lovechat.myselve.lovechat.ui.fragments.RegisterFragment

class StartActivity : AppCompatActivity(), RegisterFragment.OnRegisterFragmentListener {


    override fun userRegisterByGoogle( ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun userRegisterByEmailPassword(user: User) {
        // Запрос по возможности сделать  в отдельном потоке
        // Тут делаем запрос, в  другом в подписчике добпвляем в преференсы если пользователь успешно зареган
        UserManagerInternetImpl.instance.registerUser(user)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_fragment_container)


        // При оегистрации нового пользователя в приложение сохраняет в преференсах, что пользователь существует
        // И с шешированием пароля и логина сохраняется тоже в преференсы логин и пароль пользователя,
        // Которые подставляются автоматически при последующем входе
        // Это чтобы не показывать окно регистрации, а сразу переходить к чату

        when {
            UserManagerLocaleImpl.instance.checkUser(context = applicationContext) -> showUserRegister(
                savedInstanceState
            )
            else -> showDefaultWindow()
        }
    }

    private fun showDefaultWindow() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showUserRegister(savedInstanceState: Bundle?) {
        // If first starting activity
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .add(R.id.main_fragment_container, RegisterFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, RegisterFragment())
                .commit()
        }
    }
}

