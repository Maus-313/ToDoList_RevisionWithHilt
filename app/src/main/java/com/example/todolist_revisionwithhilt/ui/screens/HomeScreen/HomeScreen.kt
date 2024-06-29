package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import android.util.Log
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolist_revisionwithhilt.RoomDB.RoomDao
import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData
import com.example.todolist_revisionwithhilt.util.Routes
import com.example.todolist_revisionwithhilt.ui.screens.components.TaskItem.TaskItem
import com.example.todolist_revisionwithhilt.util.UiEvents


@Composable
fun HomeScreen(
    navController: NavController,
    dao: RoomDao
){
    attempt1(navController,dao)
}


@Composable
private fun attempt1(
    navController: NavController,
    dao: RoomDao
){

    val homeScreenViewModel = viewModel<HomeScreenViewModel>(
        factory = HomeScreenViewModelFactory(dao)
    )

    LaunchedEffect(key1 = true) {
        homeScreenViewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvents.NavigateTo -> {
                    navController.navigate(Routes.TaskScreen)
                }
            }
        }
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(5.dp),
                onClick ={
                    homeScreenViewModel.onEvent(HomeScreenEvent.OnClickAddTask)

                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Button")
            }
        }
    ){

        val taskItems = homeScreenViewModel.state.collectAsState().value.tasks

        LazyColumn (
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ){
            homeScreenViewModel.getAllTasks()
            items(taskItems) { task ->
                TaskItem(id = task.id, title = task.title, description = task.description)
            }
        }
    }
}