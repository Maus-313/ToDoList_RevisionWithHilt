package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist_revisionwithhilt.RoomDB.RoomDao

class HomeScreenViewModelFactory(
    private val dao: RoomDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeScreenViewModel(dao) as T
    }
}