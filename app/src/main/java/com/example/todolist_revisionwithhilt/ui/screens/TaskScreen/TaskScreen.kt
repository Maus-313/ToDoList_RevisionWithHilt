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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolist_revisionwithhilt.RoomDB.RoomDao


@Composable
fun TaskScreen(dao: RoomDao, navController: NavController){
    attempt1(dao,navController)
}

@Composable
private fun attempt1(dao: RoomDao, navController: NavController){

    val taskScreenViewmodel = viewModel<TaskScreenViewModel>(
        factory = TaskScreenViewModelFactory(dao,navController)
    )

    val title = taskScreenViewmodel.state.collectAsState().value.title.value
    val description = taskScreenViewmodel.state.collectAsState().value.description.value

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextField(
            value = title,
            onValueChange = {
                taskScreenViewmodel.updateTitle(it)
            },
            modifier = Modifier.clip(RoundedCornerShape(5.dp)),
            label = {
                Text(text = "Give Title Here!")
            }
        )
        TextField(
            value = description,
            onValueChange = {
                taskScreenViewmodel.updateDescription(it)
            },
            modifier = Modifier.clip(RoundedCornerShape(5.dp)),
            label = {
                Text(text = "Give description Here!")
            }
        )
        Button(onClick = {
            taskScreenViewmodel.addTask()
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Button")
        }
    }
}