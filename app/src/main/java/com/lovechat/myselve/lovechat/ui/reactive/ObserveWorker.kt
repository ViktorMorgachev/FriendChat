package com.lovechat.myselve.lovechat.ui.reactive

import android.support.annotation.MainThread
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.Scheduler
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

    fun observe(observeWorkerListener: ObserveWorkerListener): Observable<Boolean> {


        this.mCallBackListener = observeWorkerListener
        val editTextPasswordObservable = observables.get(0)
        val editTextEmailObservable = observables.get(1)

        // val array = mutableListOf<Boolean>()

        editTextPasswordObservable.debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { v ->
                    mCallBackListener.setPasswordInfo(v)
                }
            )

        editTextEmailObservable.debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { v ->
                    // Тоже самое
                    mCallBackListener.setEmailInfo(v)
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