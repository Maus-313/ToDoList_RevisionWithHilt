package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

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

class HomeScreenViewModel(
    private val dao: RoomDao
): ViewModel() {

    private val _state = MutableStateFlow(HomeState(dao.getAllTasks()))
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HomeScreenEvents){
        when(event){
            is HomeScreenEvents.OnDeleteTask -> {
                viewModelScope.launch(Dispatchers.IO){
                    dao.deleteTask(event.task)
                }
            }
            is HomeScreenEvents.OnClickAddTask -> {
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