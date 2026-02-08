package com.app.apptodo.apptodo.list

import com.app.apptodo.data.Task

interface TodoListContract {
    interface View {
        fun showTaskRemoved(task: Task)
        fun returnTasks(tasks: List<Task>)
        fun navigateToAddTaskFragment()
    }

    interface Presenter {
        fun onAddTaskButtonClicked()
        fun loadTasks()
        fun removeTaskLongClick(task: Task): Boolean
    }
}