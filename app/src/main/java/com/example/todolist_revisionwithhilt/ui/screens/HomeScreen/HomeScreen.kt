package com.example.todolist_revisionwithhilt.ui.screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolist_revisionwithhilt.RoomDB.RoomDao
import com.example.todolist_revisionwithhilt.util.Routes
import com.example.todolist_revisionwithhilt.ui.screens.components.TaskItem.TaskItem
import com.example.todolist_revisionwithhilt.util.UiEvents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
){
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val scaffoldState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvents.NavigateTo -> {
                    navController.navigate(Routes.TaskScreen(event.id))
                }
                is UiEvents.ShowSnackBar -> {
                    val result =  scaffoldState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if(result == SnackbarResult.ActionPerformed){
                        viewModel.onEvent(HomeScreenEvents.UnDoDeleteClick)
                    }
                }
                else -> {
                }
            }
        }
    }

    val tasks = viewModel.state.collectAsState().value.tasks.collectAsState(initial = emptyList()).value
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ToDoList")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF745A25)
                ),
                modifier = Modifier
                    .padding(
                        start = 5.dp,
                        end = 5.dp,
                        top = statusBarPadding.calculateTopPadding(),
                        bottom = 5.dp
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clip(RoundedCornerShape(5.dp))
                ,
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = scaffoldState)
        },
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
            horizontalAlignment = Alignment.End
        ){
            items(tasks){ task ->
                TaskItem(task = task, viewModel = viewModel)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(

)
@Composable
fun s(){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ToDoList")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF745A25)
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clip(RoundedCornerShape(5.dp))
                ,
            )
        }
    ){
        Column(
            modifier = Modifier.padding(it)
        ) {

        }
    }
}