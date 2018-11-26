package com.lovechat.myselve.lovechat.logic.layers.database.data

data class User(val password : CharSequence, val email : CharSequence, val nick : CharSequence = "User${Math.random()*1000}")
