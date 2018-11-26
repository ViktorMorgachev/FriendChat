package com.lovechat.myselve.lovechat.logic.layers.database.interfaces

import com.lovechat.myselve.lovechat.logic.layers.database.data.Message

interface MessageManagerInternet {
    fun sendMessage(message : Message)
    fun deleteMessage(message : Message)
    fun editMessage(message : Message)

}