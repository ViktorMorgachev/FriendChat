package com.lovechat.myselve.lovechat.ui.activities

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.lovechat.myselve.lovechat.R
import java.time.Duration

class SearchableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_fragment_container)

        if(intent.action == Intent.ACTION_SEARCH){
            intent.getStringExtra(SearchManager.QUERY).also {
                toast(intent.getStringExtra(SearchManager.QUERY))
            }
        }
    }

    fun Activity.toast(text : CharSequence, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(applicationContext, text, duration).show()
    }
}