package com.example.todolist_revisionwithhilt.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData

@Dao
interface RoomDao {

    @Insert
    fun addTask(task: TaskItemData)

    @Delete
    fun deleteTask(task: TaskItemData)

    @Query("SELECT * FROM TaskItemData")
    fun getAllTasks(): List<TaskItemData>
}