package com.barkatme.data.model.local.purchase

class PurchaseBox : Purchase {
    private val purchases = mutableListOf<Purchase>()

    fun add(purchase: Purchase) = purchases.add(purchase)

    override val price: Int
        get() = purchases.sumOf { it.price }
}

