package com.app.apptodo.apptodo.edit

import com.app.apptodo.data.Task

interface TodoEditTaskContract {
    interface View {
        fun onTaskUpDated(task: Task)
        fun showBindTask(task: Task)
        fun closeFragment()
        fun bindObserveTextChanges(task: Task)
    }
    interface Presenter {
        fun loadTask(id: Int)
        fun upDateTaskArrow(newTask: Task)
        fun upDateTaskBack(newTask: Task)
    }
}