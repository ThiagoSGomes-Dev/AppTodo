package com.app.apptodo.apptodo

class AppTodoContract {
    // TODO: Retona os dados para a view
    interface View {
        fun returnTasks(tasks: MutableList<Task>)
    }

    // TODO: Recebe os cliques da View, chama o Repository e devolve a resposta para a view
    interface Presenter {
        fun loadTasks()
        fun addTask(task: Task)
    }
}