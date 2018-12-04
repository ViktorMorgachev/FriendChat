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
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class ObserveWorker private constructor() {

    interface ObserveWorkerListener {
        fun setEmailInfo(text: CharSequence)
        fun setPasswordInfo(text: CharSequence)
    }

    private lateinit var mCallBackListener: ObserveWorkerListener
    private var observables: MutableList<Observable<String>> = mutableListOf()


    companion object Factory {

        private val ourInstance = ObserveWorker()

        fun getInstance(): ObserveWorker {
            return ourInstance
        }
    }

    fun observe(observeWorkerListener: ObserveWorkerListener): Observable<List<Boolean>> {


        this.mCallBackListener = observeWorkerListener
        val editTextPasswordObservable = observables.get(0)
        val editTextEmailObservable = observables.get(1)

        val array = mutableListOf<Boolean>()

        editTextPasswordObservable.debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { v ->
                    // Разобраться с потоками
                    mCallBackListener.setPasswordInfo(v)
                }
            )

        editTextEmailObservable.debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { v ->
                    // Тоже самое
                    mCallBackListener.setEmailInfo(v)
                }
            )

        return Observable.fromArray(
        )
    }


    // Сюда запихиваем едитТекст и события ввода оборачиваем в PublishSubject
    fun setTextWatcherObsevable(
        editTextEmail: EditText,
        editTextPassword: EditText
    ): ObserveWorker {


        val publishSubjectPassword: PublishSubject<String> = PublishSubject.create()
        val publishSubjectEmail: PublishSubject<String> = PublishSubject.create()
        // Наблюдюдать в главном потоке, даннве буду генеритя в торугом потоке
        // publishSubjectEmail.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
        //  publishSubjectPassword.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())


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


}