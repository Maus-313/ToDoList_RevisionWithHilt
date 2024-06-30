package com.example.todolist_revisionwithhilt.ui.screens.TaskScreen

sealed class TaskScreenEvents {

    data class UpdateTitle(val text: String): TaskScreenEvents()
    data class UpdateDescription(val text: String): TaskScreenEvents()
    object AddTask: TaskScreenEvents()

}