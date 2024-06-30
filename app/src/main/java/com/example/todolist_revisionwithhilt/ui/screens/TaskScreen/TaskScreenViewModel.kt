package com.example.todolist_revisionwithhilt.ui.screens.TaskScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.todolist_revisionwithhilt.RoomDB.RoomDao
import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskScreenViewModel(
    private val dao: RoomDao,
    private val navController: NavController
): ViewModel(){

    private val _state = MutableStateFlow(TaskState())
    val state = _state.asStateFlow()


    fun updateTitle(text: String){
        _state.value.title.value = text
    }

    fun updateDescription(text: String){
        _state.value.description.value = text
    }

    fun addTask(){
        val task = TaskItemData(0, state.value.title.value, state.value.description.value)
        viewModelScope.launch(Dispatchers.IO) {
            dao.addTask(task)
            withContext(Dispatchers.Main){
                Log.d("MAUS","Inside withContext")
                navController.popBackStack()
                _state.value = _state.value.copy(
                    title = mutableStateOf(""),
                    description = mutableStateOf("")
                )
            }
        }
    }
}