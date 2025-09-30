package com.app.apptodo.dda

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Model (var value: String = "")

fun processModel(model: Model) {
    println("${model.value}")
}

data class Person(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val age: Int
)

data object Open {

    val model = Model()
    var list = mutableListOf<Person>(
        Person(
            "Thiago",
            "Gomes",
            25
        ),
        Person(
            "Jessica",
            "Gomes",
            28
        )
    )

    fun listString() {
        list.map { it.toString() }
    }

}

fun demo(x: Any) {
    if (x is String) {
        print(x.length) // x is automatically cast to String
    }
}

fun main(){

}
