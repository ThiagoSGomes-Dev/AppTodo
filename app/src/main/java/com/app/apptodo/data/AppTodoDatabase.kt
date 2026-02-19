package com.app.apptodo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 2)
abstract class AppTodoDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private const val DATABASE_NAME = "task_database"
        private var instance: AppTodoDatabase? = null

        fun initialize(context: Context): AppTodoDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = AppTodoDatabase::class.java,
                name = DATABASE_NAME
            ).addMigrations(MIGRATION_1_2).build().also { inst -> instance = inst }
        }

        fun getInstance(): AppTodoDatabase = instance as AppTodoDatabase
    }
}