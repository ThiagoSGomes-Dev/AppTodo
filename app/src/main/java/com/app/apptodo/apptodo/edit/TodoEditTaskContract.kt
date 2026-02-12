package com.app.apptodo.apptodo.edit

import com.app.apptodo.data.Task

interface TodoEditTaskContract {
    interface View {
        fun onTaskUpDated(task: Task)
        fun showBindTask(task: Task)
        fun closeFragment()
    }
    interface Presenter {
        fun loadTask(id: Int)
        fun upDateTask(newTask: Task)
    }
}