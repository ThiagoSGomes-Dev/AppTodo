package com.app.apptodo.apptodo.list

import com.app.apptodo.data.AppTodoDatabase
import com.app.apptodo.data.Task
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface ListRepository {
    fun getTasks(): Flowable<List<Task>>
    fun removeTask(task: Task): Completable
    fun upDateTask(task: Task): Completable
}

class ListRepositoryImplementation(): ListRepository {

    private val dataBase = AppTodoDatabase.getInstance()

    override fun removeTask(task: Task): Completable {
        return dataBase.taskDao().delete(task)
    }

    override fun upDateTask(task: Task): Completable {
        return dataBase.taskDao().update(task)
    }

    override fun getTasks(): Flowable<List<Task>> {
        return dataBase.taskDao().getAll()
    }
}