package com.example.todolist_revisionwithhilt.ui.screens.components.TaskItem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist_revisionwithhilt.R
import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData
import com.example.todolist_revisionwithhilt.ui.screens.HomeScreen.HomeScreenEvents
import com.example.todolist_revisionwithhilt.ui.screens.HomeScreen.HomeScreenViewModel
import com.example.todolist_revisionwithhilt.ui.theme.Purple80

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
            .background(color = Purple80, shape = RoundedCornerShape(10.dp)),
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
                Modifier.padding(start = 15.dp),
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

@Preview
@Composable
fun TaskItemPrev(
//    task: TaskItemData,
//    viewModel: HomeScreenViewModel

    @PreviewParameter(SampleStringProvider::class) task: String
){

    Column(
        modifier = Modifier
            .padding(5.dp)
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(10.dp))
            .clickable {
//                viewModel.onEvent(HomeScreenEvents.OnTaskClicked(task.id))
            }
            .background(color = Purple80, shape = RoundedCornerShape(10.dp)),
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
                text = task,
                Modifier.padding(start = 15.dp),
                color = Color.Black
            )
            IconButton(onClick = {
//                viewModel.onEvent(HomeScreenEvents.OnDeleteTask(task))
            }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete",
                )
            }
        }
    }
}

class SampleStringProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf(
        "Task 1",
        "Task 2: A longer task description",
        "Task 3 with some details"
    )
}