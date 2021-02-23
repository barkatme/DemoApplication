package com.barkatme.data.model.local.purchase

fun main() {
    val box = getPurchase()
    box.printPrice()
}

fun getPurchase(): Purchase {
    val box = PurchaseBox()
    val insideBox = PurchaseBox()
    insideBox.add(PurchaseItem(5))
    insideBox.add(PurchaseItem(2))
    box.add(insideBox)
    box.add(PurchaseItem(3))
    return box
}