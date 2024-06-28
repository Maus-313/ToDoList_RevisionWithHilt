package com.example.todolist_revisionwithhilt.RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskItemData(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val title: String,
    val description: String,
)
