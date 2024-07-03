package com.example.todolist_revisionwithhilt.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(task: TaskItemData)

    @Delete
    fun deleteTask(task: TaskItemData)

    @Query("SELECT * FROM TaskItemData")
    fun getAllTasks(): Flow<List<TaskItemData>>

    @Query("select * from TaskItemData where id = :id")
    fun getTaskById(id: Int): Flow<TaskItemData>

}