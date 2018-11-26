package com.lovechat.myselve.lovechat.ui.reactive

import android.util.Log
import android.widget.EditText
import io.reactivex.Observable

class ObserveWorker private constructor(){

    private lateinit var observables : List<Observable<String>>
    private lateinit var mEditTexts : List<EditText>
    private lateinit var observableResult  : Observable<Boolean>

    companion object Factory {

        private var isCorrectPassword : Boolean = false
        private var isCorrectEmail : Boolean = false

        private val ourInstance = ObserveWorker()

        fun getInstance(): ObserveWorker {
            return ourInstance
        }
    }

    fun setObservables(observables: List<Observable<String>>){
        this.observables = observables
    }

    fun setEditTexts(editText: List<EditText>){
        this.mEditTexts = editText
    }

    fun getObservableResult() : Observable<Boolean>{
        return this.observableResult
    }

}