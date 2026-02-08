package com.app.apptodo.data

import com.app.apptodo.data.Task


interface AppTodoRepository {
    fun addTask(name: Task)
    fun readTask(): MutableList<Task>
    fun upDateTask(id: Int, newName: Task): Boolean
    fun isCompleted(id: Int): Boolean
    fun removeTask(task: Task): Boolean
    fun removeIsCompeted(id: Int): Boolean
}

class AppTodoRepositoryImplementation(
    private val data: MutableList<Task> = Data.list,
): AppTodoRepository {

    override fun addTask(name: Task) {
        data.add(name)
    }

    override fun readTask(): MutableList<Task> {
        return data
    }

    override fun upDateTask(id: Int, newName: Task): Boolean =
        data.find { taskId -> taskId.id == id }?.let {
            it.name = newName.name
            true
        } ?: false

    override fun isCompleted(id: Int): Boolean =
        data.find { taskId -> taskId.id == id }?.let {
            it.isCompleted = true
            true
        } ?: false

    override fun removeTask(task: Task): Boolean {
        val index = data.indexOfFirst{ taskId -> taskId.id == task.id }
        return if (index != -1) {
            data.removeAt(index)
            true
        } else false
    }

    override fun removeIsCompeted(id: Int): Boolean =
        data.find { taskId -> taskId.id == id && taskId.isCompleted }?.let {
            data.remove( it)
            true
        } ?: false
}

data object Data {
    var list = mutableListOf<Task>()
}