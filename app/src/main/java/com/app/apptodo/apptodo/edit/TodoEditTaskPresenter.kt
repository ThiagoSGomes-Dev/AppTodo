package com.app.apptodo.apptodo.edit

import com.app.apptodo.data.Task
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TodoEditTaskPresenter(
    private val view: TodoEditTaskContract.View?,
    private val repository: TodoEditTaskRepository
): TodoEditTaskContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun loadTask(id: Int) {
        repository.getTaskById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { task -> view?.showBindTask(task = task) }
            .also { newDisposable -> disposable.add(newDisposable) }
    }

    override fun upDateTaskArrow(newTask: Task) {
       repository.saveTask(newTask)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe {
               view?.closeFragment()
           }
           .also { newDisposable -> disposable.add(newDisposable) }
    }

    override fun upDateTaskBack(newTask: Task) {
        repository.saveTask(newTask)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view?.bindObserveTextChanges(newTask)
            }
            .also { newDisposable -> disposable.add(newDisposable) }
    }

    fun onDestroyView() {
        disposable.clear()
    }
}
