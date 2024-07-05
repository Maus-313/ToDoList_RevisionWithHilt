package com.example.todolist_revisionwithhilt.ui.screens.TaskScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

data class TaskState(
    val id: MutableState<Int> = mutableIntStateOf(0),
    val title: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf(""),
)
