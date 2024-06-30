package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData
import com.example.todolist_revisionwithhilt.util.Routes

sealed class HomeScreenEvent {

    data class OnDeleteTask(val task: TaskItemData): HomeScreenEvent()
    object OnClickAddTask: HomeScreenEvent()
}