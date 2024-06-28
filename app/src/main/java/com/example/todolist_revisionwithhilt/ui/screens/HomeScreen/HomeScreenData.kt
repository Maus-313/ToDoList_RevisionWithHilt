package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData

data class HomeScreenData(
    val taskList: MutableList<TaskItemData>
)