package com.app.apptodo

import android.app.Application
import com.app.apptodo.data.AppTodoDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppTodoDatabase.initialize(this)
    }

}