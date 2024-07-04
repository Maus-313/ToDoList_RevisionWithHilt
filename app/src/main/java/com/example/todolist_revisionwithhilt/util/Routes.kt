package com.example.todolist_revisionwithhilt.util

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    object HomeScreen

    @Serializable
    data class TaskScreen(val id: Int)

}