package com.example.todolist_revisionwithhilt.ui.screens.TaskScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.todolist_revisionwithhilt.RoomDB.RoomDao

class TaskScreenViewModelFactory(
    private val dao: RoomDao,
    private val navController: NavController
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskScreenViewModel(dao,navController) as T
    }
}