package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist_revisionwithhilt.Repository.RoomRepo
import com.example.todolist_revisionwithhilt.util.UiEvents
import com.example.todolist_revisionwithhilt.util.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repo: RoomRepo
): ViewModel() {

    private val _state = MutableStateFlow(HomeState(repo.getAllTasks()))
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HomeScreenEvents){
        when(event){
            is HomeScreenEvents.OnDeleteTask -> {
                viewModelScope.launch(Dispatchers.IO){
                    repo.deleteTask(event.task)
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