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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolist_revisionwithhilt.RoomDB.RoomDao
import com.example.todolist_revisionwithhilt.RoomDB.TaskItemData
import com.example.todolist_revisionwithhilt.TaskScreen
import com.example.todolist_revisionwithhilt.ui.screens.components.TaskItem.TaskItem


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

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(5.dp),
                onClick ={
                    navController.navigate(TaskScreen)
                    Log.d("MAUS","AddTask Clicked!")
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


fun getSampleTaskItems(): List<TaskItemData> {
    return listOf(
        TaskItemData(1, "Grocery Shopping", "Buy milk, eggs, bread, and cheese from the supermarket."),
        TaskItemData(2, "Book Doctor Appointment", "Schedule a check-up appointment with Dr. Smith."),
        TaskItemData(3, "Pay Bills", "Pay electricity and internet bills before the due date."),
        TaskItemData(4, "Finish Project Report", "Complete the final draft of the project report and submit it."),
        TaskItemData(5, "Call Mom", "Remember to call Mom and wish her a happy birthday."),
        TaskItemData(6, "Clean House", "Wipe down the house and vacuum the floor."),
        TaskItemData(7, "Call Parents", "Call your parents and wish them a happy birthday."),
        TaskItemData(8, "Grocery Shopping", "Buy milk, eggs, bread, and cheese from the supermarket."),
        TaskItemData(9, "Book Doctor Appointment", "Schedule a check-up appointment with Dr. Smith."),
        TaskItemData(10, "Pay Bills", "Pay electricity and internet bills before the due date."),
        TaskItemData(11, "Finish Project Report", "Complete the final draft of the project report and submit it."),
        TaskItemData(12, "Call Mom", "Remember to call Mom and wish her a happy birthday."),
        TaskItemData(13, "Clean House", "Wipe down the house and vacuum the floor."),
        TaskItemData(14, "Call Parents", "Call your parents and wish them a happy birthday."),
        TaskItemData(15, "Grocery Shopping", "Buy milk, eggs, bread, and cheese from the supermarket.",)
    )
}