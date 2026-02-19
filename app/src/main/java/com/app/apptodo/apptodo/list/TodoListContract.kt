package com.app.apptodo.apptodo.list

import com.app.apptodo.data.Task

interface TodoListContract {
    interface View {
        fun bindEmptyState()
        fun navigationToEdit(taskId: Int)
        fun showIsFavoriteUpdate(task: Task)
        fun showTaskUpDate(task: Task)
        fun showTaskRemoved(task: Task)
        fun returnTasks(tasks: List<Task>)
        fun navigateToAddTaskFragment()
    }

    interface Presenter {
        fun onClickedEditView(taskId: Int)
        fun toggleFavorite(task: Task)
        fun loadTasks()
        fun onAddTaskButtonClicked()
        fun onTaskClicked(task: Task)
        fun onTaskLongClicked(task: Task)
        fun onDestroyView()
    }
}