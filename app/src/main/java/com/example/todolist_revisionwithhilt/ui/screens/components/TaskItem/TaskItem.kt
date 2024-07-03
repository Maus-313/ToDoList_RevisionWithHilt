package com.example.todolist_revisionwithhilt.ui.screens.components.TaskItem

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData
import com.example.todolist_revisionwithhilt.ui.screens.HomeScreen.HomeScreenEvents
import com.example.todolist_revisionwithhilt.ui.screens.HomeScreen.HomeScreenViewModel

@Composable
fun TaskItem(
    task: TaskItemData,
    viewModel: HomeScreenViewModel
){

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(BorderStroke(1.dp, Color.Black),RoundedCornerShape(10.dp))
            .clickable {
                viewModel.onEvent(HomeScreenEvents.OnTaskClicked(task.id))
            },
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
            IconButton(onClick = { viewModel.onEvent(HomeScreenEvents.OnDeleteTask(task)) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}