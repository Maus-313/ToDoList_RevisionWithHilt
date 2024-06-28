package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData

data class HomeState(
    val tasks: List<TaskItemData>
)