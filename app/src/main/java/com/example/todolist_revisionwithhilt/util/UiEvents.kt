package com.example.todolist_revisionwithhilt.util

sealed class UiEvents {
    data class NavigateTo(val route: Routes.TaskScreen): UiEvents()
    object PopBackStack: UiEvents()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ): UiEvents()
}