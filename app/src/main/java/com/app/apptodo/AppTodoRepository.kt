package com.app.apptodo

interface AppTodoRepository {
    fun addItem(item: String)
    fun getList(): MutableList<String>
}

class AppTodoRepositoryImplementation: AppTodoRepository {
    val data = Data.list

    override fun addItem(item: String) {
        data.add(item)
    }

    override fun getList(): MutableList<String> {
        return data
    }
}

object Data {
    val list = mutableListOf<String>()
}