package com.lovechat.myselve.lovechat.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.BindViews
import butterknife.ButterKnife
import com.lovechat.myselve.lovechat.R
import com.lovechat.myselve.lovechat.logic.interactors.KeysCommonInteractor
import com.lovechat.myselve.lovechat.models.data.User
import com.lovechat.myselve.lovechat.ui.reactive.ObserveWorker
import io.reactivex.Observable
import kotlinx.android.synthetic.main.register_fragment_layout.*


class RegisterFragment : Fragment(), View.OnClickListener, ObserveWorker.ObserveWorkerListener {


    override fun setEmailInfoError(msg: CharSequence) {
        mTextViewInfoEmail.setTextColor(Color.RED)
        mTextViewInfoEmail.setText(msg)
    }

    override fun setPasswordInfoError(msg: CharSequence) {
        mTextViewInfoPassword.setTextColor(Color.RED)
        mTextViewInfoPassword.setText(msg)
    }

    override fun activateButton() {
        Log.d(KeysCommonInteractor.KeysField.LOG_TAG, javaClass.canonicalName + " activateButton()")
        mButton.setOnClickListener(this@RegisterFragment)
    }

    override fun setEmailInfo(text: CharSequence) {
        mTextViewInfoEmail.setTextColor(Color.GREEN)
        mTextViewInfoEmail.text = text
    }

    override fun setPasswordInfo(text: CharSequence) {
        mTextViewInfoPassword.setTextColor(Color.GREEN)
        mTextViewInfoPassword.text = text
    }



    // Any Fields
    private lateinit var mObserveWorker: ObserveWorker
    private lateinit var observer: List<Observable<Boolean>>
    private lateinit var mSharedPreferences: SharedPreferences

    // Views
    @BindView(R.id.btn_register)
    private lateinit var mButton: Button

    @BindView(R.id.et_password)
    private lateinit var mEditTextPassword: TextInputEditText

    @BindView(R.id.et_email)
    private lateinit var mEditTextEmail: TextInputEditText

    @BindView(R.id.iv_google_enter)
    private lateinit var mImageViewGoogle: ImageView

    @BindView(R.id.tv_info_email)
    private lateinit var mTextViewInfoEmail: TextView

    @BindView(R.id.tv_info_password)
    private lateinit var mTextViewInfoPassword: TextView


    // CallBack
    lateinit var mCallBackClickListener: OnRegisterFragmentListener;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.register_fragment_layout, null)

        ButterKnife.bind(this, view)

        mObserveWorker = ObserveWorker.getInstance()
        mObserveWorker.setTextWatcherObsevable(mEditTextEmail, mEditTextPassword)

        // Запускаем слушатель
        mObserveWorker.observe(this, context)



        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        //  mButton.setOnClickListener(this)
        mImageViewGoogle.setOnClickListener(this)

        // Кнопке прописать setOnClickListener только при условии что пользователь ввёл всё правильно
        return view
    }


    override fun onClick(view: View) {


        // Отписываемся
       // ObserveWorker.getInstance().unsubscribe()

        val snackbar =
            Snackbar.make(root_layout, "Button was activated", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Ok", {})
        snackbar.show()


        val user = User(
            password = mEditTextPassword.text.toString(),
            email = mEditTextEmail.text.toString()
        )


        when {
            view is ImageView -> mCallBackClickListener.userRegisterByGoogle()
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
        fun userRegisterByGoogle()
        fun userRegisterByEmailPassword(user: User)
    }

}