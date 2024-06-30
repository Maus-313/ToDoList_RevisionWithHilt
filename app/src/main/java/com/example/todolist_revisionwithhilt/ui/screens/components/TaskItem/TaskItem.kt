package com.example.todolist_revisionwithhilt.ui.screens.components.TaskItem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData
import com.example.todolist_revisionwithhilt.ui.screens.HomeScreen.HomeScreenEvent
import com.example.todolist_revisionwithhilt.ui.screens.HomeScreen.HomeScreenViewModel


@Composable
fun TaskItem(
    task: TaskItemData,
    viewModel: HomeScreenViewModel
){
    attempt1(task, viewModel)
}

@Composable
private fun attempt1(task: TaskItemData, viewModel: HomeScreenViewModel){

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp)),
        border = BorderStroke(1.dp, Color.Black),
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Reorder, contentDescription = "Reorder")
            }
            Text(text = task.title)
            IconButton(onClick = { viewModel.onEvent(HomeScreenEvent.OnDeleteTask(task)) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}