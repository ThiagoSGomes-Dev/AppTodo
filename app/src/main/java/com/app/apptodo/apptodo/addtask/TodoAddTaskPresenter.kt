package com.app.apptodo.apptodo.addtask

import com.app.apptodo.data.Task
import com.app.apptodo.data.AppTodoRepository

class TodoAddTaskPresenter(
    private val view: TodoAddTaskContract.View?,
    private val repository: AppTodoRepository
): TodoAddTaskContract.Presenter {
    override fun onAddTaskClicked( task: Task) {
        repository.addTask( task)
        view?.showAddTask(task)
        view?.navigateToListFragment()
    }
}