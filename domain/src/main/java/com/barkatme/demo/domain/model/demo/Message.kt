package com.barkatme.demo.domain.model.demo

data class Message(
    val id: Int = 0,
    val nickName: String = "",
    val time: Long = System.currentTimeMillis(),
    val text: String = "",
)