package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import com.example.todolist_revisionwithhilt.util.Routes

sealed class HomeScreenEvent {

    data class OnDeleteTask(val id: Int): HomeScreenEvent()
    object GetAllTasks: HomeScreenEvent()
    object OnClickAddTask: HomeScreenEvent()

}