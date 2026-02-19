package com.app.apptodo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(task: Task): Completable

    @Update
    fun update(task: Task): Completable

    @Delete
    fun delete(task: Task): Completable

    @Query("SELECT * FROM table_task WHERE id = :id")
    fun getItemById(id: Int): Single<Task>

    @Query("SELECT * FROM table_task")
    fun getAll(): Flowable<List<Task>>
}