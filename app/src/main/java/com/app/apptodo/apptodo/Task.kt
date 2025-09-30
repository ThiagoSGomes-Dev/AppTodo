package com.app.apptodo.apptodo

data class Task(
    val name: String = ""
) {
    override fun toString(): String {
        return name
    }
}
