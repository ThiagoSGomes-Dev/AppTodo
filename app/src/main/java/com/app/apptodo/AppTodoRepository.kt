package com.app.apptodo

import com.app.apptodo.apptodo.Task
import java.io.Serial


interface AppTodoRepository {
    fun addItem(item: Task)
    fun getList(): MutableList<Task>
}

class AppTodoRepositoryImplementation: AppTodoRepository {
    val data = Data.list

    override fun addItem(item: Task) {
        data.add(item)
    }

    override fun getList(): MutableList<Task> {
        return data
    }
}

data object Data {
    var list = mutableListOf<Task>()
}