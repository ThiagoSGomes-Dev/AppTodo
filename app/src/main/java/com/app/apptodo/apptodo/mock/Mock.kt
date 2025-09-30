package com.app.apptodo.apptodo.mock

data class Task(
    val name: String = ""
)

val mockList = mutableListOf(
    Task(
        "Compas"
    ),
    Task(
        "Correr"
    ),
    Task(
        "Estudar"
    )
)