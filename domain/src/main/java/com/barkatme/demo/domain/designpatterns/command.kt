package com.barkatme.demo.domain.designpatterns

private object Command {
    val prints = mutableListOf<() -> String>()

    fun addPrint(p: () -> String) {
        prints.add(p)
    }

    fun removePrint(p: () -> String) {
        prints.remove(p)
    }

    fun printAllPrints() {
        prints.forEach {
            println(it.invoke())
        }
    }
}

fun main() {
    val p1 = { "1" }
    val p2 = { "2" }
    Command.addPrint(p1)
    Command.addPrint(p2)
    println("1st time")
    Command.printAllPrints() // 1 \n 2
    Command.removePrint(p1)
    println("2nd time")
    Command.printAllPrints() // 2
}