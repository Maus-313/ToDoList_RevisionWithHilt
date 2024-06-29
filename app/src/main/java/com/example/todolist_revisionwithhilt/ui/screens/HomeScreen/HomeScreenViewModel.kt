package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist_revisionwithhilt.RoomDB.RoomDao
import com.example.todolist_revisionwithhilt.util.UiEvents
import com.example.todolist_revisionwithhilt.util.Routes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenViewModel(
    private val dao: RoomDao
): ViewModel() {

    private val _state = MutableStateFlow(HomeState(emptyList()))
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun deleteTask(id: Int){
        viewModelScope.launch {
            dao.deleteTask(state.value.tasks.find { it.id == id }!!)
        }
    }

    fun getAllTasks(){
        viewModelScope.launch{
            val tasks = withContext(Dispatchers.IO){
                dao.getAllTasks()
            }
            _state.value = HomeState(tasks)
        }
    }

    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnDeleteTask -> {

            }
            is HomeScreenEvent.GetAllTasks -> {

            }
            is HomeScreenEvent.OnClickAddTask -> {
                sendUiEvent(UiEvents.NavigateTo(Routes.TaskScreen))
            }
        }
    }

    private fun sendUiEvent(event: UiEvents){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}