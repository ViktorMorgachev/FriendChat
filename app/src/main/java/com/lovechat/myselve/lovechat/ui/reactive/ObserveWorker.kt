package com.lovechat.myselve.lovechat.ui.reactive

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class ObserveWorker private constructor() {

    private var observables: MutableList<Observable<String>> = mutableListOf()
    private var mTextViews: MutableList<TextView> = mutableListOf()
    private var mEditTexts: MutableList<EditText> = mutableListOf()


    companion object Factory {

        private val ourInstance = ObserveWorker()

        fun getInstance(): ObserveWorker {
            return ourInstance
        }
    }

    fun observe(): Observable<List<Boolean>> {


        val editTextPasswordObservable = observables.get(0)
        val editTextEmailObservable = observables.get(1)

        val array = mutableListOf<Boolean>()
        /*   val publishSubjectCorrectPassword = PublishSubject.create<Boolean>()
           val publishSubjectCorrectEmail = PublishSubject.create<Boolean>()

           // Нужно подписать publishSubjectCorrectPassword  и  на события

           editTextEmailObservable.subscribe()

           return listOf(publishSubjectCorrectEmail, publishSubjectCorrectPassword)*/

        editTextEmailObservable.debounce(500, TimeUnit.MILLISECONDS)
            .subscribe(
                { v ->

                }
            )

        editTextEmailObservable.debounce(500, TimeUnit.MILLISECONDS)
            .subscribe(
                { v ->
                    print(v)
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

        mTextViews.addAll(listOf(editTextPassword, editTextEmail))

        val publishSubjectPassword: PublishSubject<String> = PublishSubject.create()
        val publishSubjectEmail: PublishSubject<String> = PublishSubject.create()

        editTextPassword.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(editable: Editable?) {
                publishSubjectPassword.onNext(editable.toString())

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        editTextEmail.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(editable: Editable?) {
                publishSubjectEmail.onNext(editable.toString())

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        observables.addAll(listOf(publishSubjectPassword, publishSubjectEmail))
        return this
    }

    fun setTextViews(editTexts: MutableList<TextView>) {
        this.mTextViews = editTexts
    }


}