package com.example.todolist_revisionwithhilt.ui.screens.components.TaskItem

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData
import com.example.todolist_revisionwithhilt.ui.screens.HomeScreen.HomeScreenEvents
import com.example.todolist_revisionwithhilt.ui.screens.HomeScreen.HomeScreenViewModel

@Composable
fun TaskItem(
    task: TaskItemData,
    viewModel: HomeScreenViewModel
){

    Column(
        modifier = Modifier
            .padding(5.dp)
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(10.dp))
            .clickable {
                viewModel.onEvent(HomeScreenEvents.OnTaskClicked(task.id))
            }
            .background(color = Color(0xFF745A25), shape = RoundedCornerShape(10.dp)),
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
//            TODO: add checkbox
//            Checkbox(
//                checked = false,
//                onCheckedChange = {},
//                colors = CheckboxDefaults.colors(Color(0xFF745A25))
//            )
            Text(
                text = task.title,
                Modifier.padding(start = 5.dp),
                color = Color.White
            )
            IconButton(onClick = {
                viewModel.onEvent(HomeScreenEvents.OnDeleteTask(task))
            }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete",
                )
            }
        }
    }
}