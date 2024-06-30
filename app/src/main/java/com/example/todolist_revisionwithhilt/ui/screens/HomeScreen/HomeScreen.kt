package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolist_revisionwithhilt.RoomDB.RoomDao
import com.example.todolist_revisionwithhilt.util.Routes
import com.example.todolist_revisionwithhilt.ui.screens.components.TaskItem.TaskItem
import com.example.todolist_revisionwithhilt.util.UiEvents

@Composable
fun HomeScreen(
    navController: NavController,
){
    val viewModel: HomeScreenViewModel = hiltViewModel()

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvents.NavigateTo -> {
                    navController.navigate(Routes.TaskScreen)
                }
            }
        }
    }

    val tasks = viewModel.state.collectAsState().value.tasks.collectAsState(initial = emptyList()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(5.dp),
                onClick ={
                    viewModel.onEvent(HomeScreenEvents.OnClickAddTask)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Button")
            }
        }
    ){
        LazyColumn (
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ){
            items(tasks){ task ->
                TaskItem(task = task, viewModel = viewModel)
            }
        }
    }
}