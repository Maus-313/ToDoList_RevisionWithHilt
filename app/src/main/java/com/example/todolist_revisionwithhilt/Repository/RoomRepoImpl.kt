package com.example.todolist_revisionwithhilt.Repository

import com.example.todolist_revisionwithhilt.RoomDB.RoomDao
import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData
import kotlinx.coroutines.flow.Flow

class RoomRepoImpl(
    private val dao: RoomDao
): RoomRepo {

    override suspend fun addTask(task: TaskItemData) {
        dao.addTask(task)
    }

    override suspend fun deleteTask(task: TaskItemData) {
        dao.deleteTask(task)
    }

    override fun getAllTasks(): Flow<List<TaskItemData>> {
         return dao.getAllTasks()
    }

}