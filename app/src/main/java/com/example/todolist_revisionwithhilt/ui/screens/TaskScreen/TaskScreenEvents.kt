package com.example.todolist_revisionwithhilt.ui.screens.TaskScreen

import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData

sealed class TaskScreenEvents {

    data class UpdateId(val id: Int): TaskScreenEvents()
    data class UpdateTitle(val text: String): TaskScreenEvents()
    data class UpdateDescription(val text: String): TaskScreenEvents()
    object AddTask: TaskScreenEvents()

}