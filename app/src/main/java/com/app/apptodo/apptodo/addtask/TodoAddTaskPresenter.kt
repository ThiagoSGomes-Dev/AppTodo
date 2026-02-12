package com.app.apptodo.apptodo.addtask

import com.app.apptodo.data.Task
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TodoAddTaskPresenter(
    private val view: TodoAddTaskContract.View?,
    private val repository: AddTaskRepository
): TodoAddTaskContract.Presenter {
    private val disposable = CompositeDisposable()

    override fun onAddTaskClicked(task: Task, description: String?, isFavorite: Boolean) {
        repository.addTask(task)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{}
            .also { disposable.add(disposable) }
    }

    override fun onDestroyView() {
        disposable.clear()
    }
}