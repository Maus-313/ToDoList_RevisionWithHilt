package com.example.todolist_revisionwithhilt.RoomDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TaskItemData::class],
    version = 1
)
abstract class ToDoDatabase: RoomDatabase() {
    abstract val dao: RoomDao
}