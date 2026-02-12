package com.app.apptodo.apptodo.addtask

import com.app.apptodo.data.Task

interface TodoAddTaskContract {
    interface View {
    }
    interface Presenter {
        fun onAddTaskClicked(task: Task, description: String?, isFavorite: Boolean)
        fun onDestroyView()
    }
}
