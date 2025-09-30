package com.app.apptodo.dda

fun main() {

    var done = mutableListOf<String>()

    fun upsDate(newDone: MutableList<String>) {
        done = newDone
    }


    fun returnList(dones : MutableList<String>) {
        upsDate(dones)
    }


    val a = true
    val b = false
    val c = true
    val d = false

    var num1 = 1
    var num2 = 3

    val num = 0

    // Operdor E (&&)
    val resulte1 = d && b
    val resulte2 = b && d

    println(resulte1)
    println(resulte2)

    val result = resulte1 || resulte2

    println(result)

    println("Números")

    // num1 = num1 + num2
    // println(num1)
    num1 += num2
    println(num1)

}