package com.example.todolist_revisionwithhilt.util

sealed class UiEvents {
    data class NavigateTo(val route: Routes.TaskScreen): UiEvents()
}