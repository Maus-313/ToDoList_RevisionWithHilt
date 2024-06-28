package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist_revisionwithhilt.RoomDB.RoomDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenViewModel(
    private val dao: RoomDao
): ViewModel() {

    private val _state = MutableStateFlow(HomeState(emptyList()))
    val state = _state.asStateFlow()

    fun deleteTask(id: Int){
        viewModelScope.launch {
            dao.deleteTask(state.value.tasks.find { it.id == id }!!)
        }
    }

    fun getAllTasks(){
        Log.d("MAUS","getAllTask Function called!")

        viewModelScope.launch{
            val tasks = withContext(Dispatchers.IO){
                dao.getAllTasks()
            }
            _state.value = HomeState(tasks)
        }
    }
}