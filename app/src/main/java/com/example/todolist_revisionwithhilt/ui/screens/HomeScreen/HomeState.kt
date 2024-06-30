package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val tasks: Flow<List<TaskItemData>>
)