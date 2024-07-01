package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData

sealed class HomeScreenEvents {

    data class OnDeleteTask(val task: TaskItemData): HomeScreenEvents()
    object OnClickAddTask: HomeScreenEvents()
    object UnDoDeleteClick: HomeScreenEvents()
}