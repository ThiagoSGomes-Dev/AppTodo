package com.app.apptodo.data

object IdGenerator {
    private var id = 0
    fun nextId(): Int {
        id++
        return id
    }
}

data class Task(
    val id: Int = IdGenerator.nextId(),
    var name: String,
    val description: String? = null,
    var isCompleted: Boolean = false,
    val isFavorite: Boolean = false,
)