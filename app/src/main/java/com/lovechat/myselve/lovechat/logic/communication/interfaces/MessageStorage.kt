package com.lovechat.myselve.lovechat.logic.communication.interfaces

interface MessageStorage {

    // Кеширование сообщений
    fun saveMessages(messsages : MutableMap<MutableList<CharSequence>, Int>)
    // Получение сообщений по id группы
    fun getMessages(id : Int) : MutableList<CharSequence>
    // Может понадобится )
    fun getAllMessages() : MutableMap<MutableList<CharSequence>, Int>


}