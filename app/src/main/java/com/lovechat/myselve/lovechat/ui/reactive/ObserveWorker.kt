package com.lovechat.myselve.lovechat.ui.reactive

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.lovechat.myselve.lovechat.R
import com.lovechat.myselve.lovechat.logic.registration.Validator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class ObserveWorker private constructor() {

    interface ObserveWorkerListener {
        fun setEmailInfo(text: CharSequence)
        fun setPasswordInfo(text: CharSequence)
        fun activateButton()
        fun setEmailInfoError(msg: CharSequence)
        fun setPasswordInfoError(msg: CharSequence)
    }

    object Crutch {
        // Это пока костыль
        private var isRightPassword = false
        private var isRightEmail = false
    }

    private lateinit var mCallBackListener: ObserveWorkerListener
    private var observables: MutableList<Observable<String>> = mutableListOf()


    companion object Factory {

        private val ourInstance = ObserveWorker()

        fun getInstance(): ObserveWorker {
            return ourInstance
        }

    }

    fun observe(
        observeWorkerListener: ObserveWorkerListener,
        context: Context
    ): Observable<Boolean> {


        this.mCallBackListener = observeWorkerListener
        val editTextPasswordObservable = observables.get(0)
        val editTextEmailObservable = observables.get(1)

        // val array = mutableListOf<Boolean>()

        editTextPasswordObservable.debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { v ->
                    when {
                        Validator.valid(
                            Validator.PASSWORD_PATTERN,
                            v
                        ) -> mCallBackListener.setPasswordInfo(context.resources.getString(R.string.right_password))
                        else -> mCallBackListener.setPasswordInfoError(context.resources.getString(R.string.wrong_password))
                    }
                }
            )

        editTextEmailObservable.debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { v ->
                    when {
                        Validator.valid(
                            Validator.EMAIL_PATTERN,
                            v
                        ) -> mCallBackListener.setEmailInfo(context.resources.getString(R.string.right_email))
                        else -> mCallBackListener.setEmailInfoError(context.resources.getString(R.string.wrong_email))
                    }

                }
            )

        return Observable
            .combineLatest(
                editTextEmailObservable,
                editTextPasswordObservable,
                BiFunction { email, password -> email.isNotEmpty() && password.isNotEmpty() })

    }


    // Сюда запихиваем едитТекст и события ввода оборачиваем в PublishSubject
    fun setTextWatcherObsevable(
        editTextEmail: EditText,
        editTextPassword: EditText
    ): ObserveWorker {


        val publishSubjectPassword: PublishSubject<String> = PublishSubject.create()
        val publishSubjectEmail: PublishSubject<String> = PublishSubject.create()



        editTextPassword.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(editable: Editable?) {
                publishSubjectPassword
                    .onNext(editable.toString())

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        editTextEmail.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(editable: Editable?) {
                publishSubjectEmail
                    .onNext(editable.toString())

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        observables.addAll(listOf(publishSubjectPassword, publishSubjectEmail))
        return this
    }

    fun unsubscribe() {
        observables.forEach {
            it.unsubscribeOn(Schedulers.computation())
        }
    }


}