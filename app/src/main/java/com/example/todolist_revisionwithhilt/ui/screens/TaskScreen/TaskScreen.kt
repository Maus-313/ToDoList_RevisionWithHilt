package com.example.todolist_revisionwithhilt.ui.screens.TaskScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolist_revisionwithhilt.RoomDB.RoomDao
import com.example.todolist_revisionwithhilt.util.UiEvents


@Composable

fun TaskScreen(
    navController: NavController,
//    id: Int
) {

    val viewModel: TaskScreenViewModel = hiltViewModel()
    val title = viewModel.state.collectAsState().value.title.value
    val description = viewModel.state.collectAsState().value.description.value

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvents.collect{ event ->
            when (event) {
                is UiEvents.PopBackStack -> {
                    navController.popBackStack()
                }
                else -> {}
            }
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextField(
            value = title,
            onValueChange = {
                viewModel.onEvent(TaskScreenEvents.UpdateTitle(it))
            },
            modifier = Modifier.clip(RoundedCornerShape(5.dp)),
            label = {
                Text(text = "Give Title Here!")
            }
        )
        TextField(
            value = description,
            onValueChange = {
                viewModel.onEvent(TaskScreenEvents.UpdateDescription(it))
            },
            modifier = Modifier.clip(RoundedCornerShape(5.dp)),
            label = {
                Text(text = "Give description Here!")
            }
        )
        Button(onClick = {
            viewModel.onEvent(TaskScreenEvents.AddTask)
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Button")
        }
    }
}