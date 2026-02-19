package com.app.apptodo.apptodo.edit

import com.app.apptodo.data.AppTodoDatabase
import com.app.apptodo.data.Task
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface TodoEditTaskRepository {
    fun getTaskById(id: Int): Single<Task>
    fun saveTask(newTask: Task): Completable
}

class TodoEditTaskRepositoryImplementation: TodoEditTaskRepository {
    private val dataBase = AppTodoDatabase.getInstance()

    override fun getTaskById(id: Int): Single<Task> {
        return dataBase.taskDao().getItemById(id)
    }

    override fun saveTask(newTask: Task): Completable {
        return dataBase.taskDao().update(newTask)
    }
}

