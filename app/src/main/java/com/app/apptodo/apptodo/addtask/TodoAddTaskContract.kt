package com.app.apptodo.apptodo.addtask

import com.app.apptodo.data.Task

interface TodoAddTaskContract {
    interface View {
        fun navigateToListFragment()
        fun showAddTask(task: Task)
    }
    interface Presenter {
        fun onAddTaskClicked(task: Task)
    }
}
