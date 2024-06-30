package com.example.todolist_revisionwithhilt.Repository

import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData
import kotlinx.coroutines.flow.Flow

interface RoomRepo {

    suspend fun addTask(task : TaskItemData)
    suspend fun deleteTask(task : TaskItemData)
    fun getAllTasks(): Flow<List<TaskItemData>>

}