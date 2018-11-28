package com.lovechat.myselve.lovechat.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.lovechat.myselve.lovechat.R
import com.lovechat.myselve.lovechat.logic.layers.database.data.User
import com.lovechat.myselve.lovechat.ui.reactive.ObserveWorker
import io.reactivex.Observable
import kotlinx.android.synthetic.main.register_fragment_layout.view.*


class RegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mButton: Button
    lateinit var mCallBackClickListener: OnRegisterFragmentListener;
    private lateinit var mEditTextPassword: TextInputEditText
    private lateinit var mEditTextEmail: TextInputEditText
    private lateinit var mImageViewGoogle: ImageView
    private lateinit var mTextViewInfoEmail: TextView
    private lateinit var mTextViewInfoPAssword: TextView
    private lateinit var mObserveWorker: ObserveWorker
    private lateinit var observablePassword: Observable<Boolean>
    private lateinit var observableEmail: Observable<Boolean>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.register_fragment_layout, null)

        mButton = view.findViewById(R.id.btn_register)
        mImageViewGoogle = view.findViewById(R.id.iv_google_enter)
        mEditTextEmail = view.findViewById(R.id.et_email)
        mEditTextPassword = view.findViewById(R.id.et_password)



        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        //  mButton.setOnClickListener(this)
        mImageViewGoogle.setOnClickListener(this)

        // Кнопке прописать setOnClickListener только при условии что пользователь ввёл всё правильно
        return view
    }

    override fun onClick(view: View) {

        val user = User(
                password = mEditTextPassword.text.toString(),
                email = mEditTextEmail.text.toString()
        )


        when {
            view is ImageView -> mCallBackClickListener.userRegisterByGoogle(user = user)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mCallBackClickListener = context as OnRegisterFragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement OnRegisterFragmentListener")
        }
    }

    interface OnRegisterFragmentListener {
        fun userRegisterByGoogle(user: User)
        fun userRegisterByEmailPassword(user: User)
    }

}