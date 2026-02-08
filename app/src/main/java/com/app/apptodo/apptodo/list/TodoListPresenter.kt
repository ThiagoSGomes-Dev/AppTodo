package com.app.apptodo.apptodo.list

import com.app.apptodo.data.AppTodoRepository
import com.app.apptodo.data.Task

class TodoListPresenter(
    private val view: TodoListContract.View?,
    private val repository: AppTodoRepository
): TodoListContract.Presenter {
    override fun removeTaskLongClick(task: Task): Boolean {
        repository.removeTask(task)
        view?.showTaskRemoved(task)
        return true
    }

    override fun onAddTaskButtonClicked() {
        view?.navigateToAddTaskFragment()
    }

    override fun loadTasks() {
        repository.readTask()
        view?.returnTasks(repository.readTask())
    }
}