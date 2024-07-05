package com.example.todolist_revisionwithhilt.ui.screens.TaskScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist_revisionwithhilt.Repository.RoomRepo
import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData
import com.example.todolist_revisionwithhilt.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val repo: RoomRepo,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    val TAG = "MAUS"

    private val _state = MutableStateFlow(TaskState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvents>()
    val uiEvents = _uiEvent.receiveAsFlow()

    init {
        val id = savedStateHandle.get<Int>("id")
        if(id!=null && id != -1){
            viewModelScope.launch {
                repo.getTaskById(id).asLiveData().observeForever{ task ->
                    if (task != null) {
                        onEvent(TaskScreenEvents.UpdateId(task.id))
                        onEvent(TaskScreenEvents.UpdateTitle(task.title))
                        onEvent(TaskScreenEvents.UpdateDescription(task.description))
                    }
                }
            }
        }
    }


    fun onEvent(event: TaskScreenEvents){
        when(event){
            is TaskScreenEvents.UpdateId -> {
                _state.value = _state.value.copy(
                    id = mutableStateOf(event.id)
                )
            }
            is TaskScreenEvents.UpdateTitle -> {
                _state.value = _state.value.copy(
                    title = mutableStateOf(event.text)
                )
            }
            is TaskScreenEvents.UpdateDescription -> {
                _state.value = _state.value.copy(
                    description = mutableStateOf(event.text)
                )
            }
            is TaskScreenEvents.AddTask -> {
                val task = TaskItemData(state.value.id.value, state.value.title.value, state.value.description.value)
                viewModelScope.launch(Dispatchers.IO) {
                    repo.addTask(task)
                }
                sendUiEvent(event = UiEvents.PopBackStack)
            }
        }
    }

    private fun sendUiEvent(event: UiEvents){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}