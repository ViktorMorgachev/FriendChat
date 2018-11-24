package com.lovechat.myselve.lovechat.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.lovechat.myselve.lovechat.R


class RegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mButton: Button
    private lateinit var mEditText: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.register_fragment_layout, null)
        mButton = view.findViewById(R.id.btn_register)
        mEditText = view.findViewById(R.id.et_email)
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        mButton.setOnClickListener(this)
        return view
    }

    override fun onClick(view: View) {

    }

}