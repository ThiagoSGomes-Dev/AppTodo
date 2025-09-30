package com.app.apptodo.apptodo

import com.app.apptodo.AppTodoRepository

class AppTodoPresenter (
    val viw : AppTodoContract.View,
    val repository : AppTodoRepository
): AppTodoContract.Presenter {
    override fun loadTasks() {
        val tasks = repository.getList()
        viw.returnTasks(tasks)
    }

    override fun addTask(task: Task) {
        repository.addItem(task)
        loadTasks()
    }
}