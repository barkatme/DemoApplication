package com.barkatme.data.model.local.purchase

interface Purchase {
    val price: Int

    fun printPrice() {
        println(price.toString())
    }
}