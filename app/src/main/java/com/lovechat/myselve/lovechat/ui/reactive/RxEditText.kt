package com.lovechat.myselve.lovechat.ui.reactive


import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


fun getTextWatherObsevable(editText: EditText) : Observable<String> {


    val publishSubject : PublishSubject<String> =  PublishSubject.create()

    editText.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(editable: Editable?) {
            publishSubject.onNext(editable.toString())

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    })

    return publishSubject
}