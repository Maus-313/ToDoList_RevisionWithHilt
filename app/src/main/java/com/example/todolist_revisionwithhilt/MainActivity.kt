package com.example.todolist_revisionwithhilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.todolist_revisionwithhilt.util.Routes
import com.example.todolist_revisionwithhilt.ui.screens.HomeScreen.HomeScreen
import com.example.todolist_revisionwithhilt.ui.screens.TaskScreen.TaskScreen
import com.example.todolist_revisionwithhilt.ui.theme.ToDoList_RevisionWithHiltTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoList_RevisionWithHiltTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.HomeScreen){
                    composable<Routes.HomeScreen>{
                        HomeScreen(
                            navController = navController
                        )
                    }
                    composable<Routes.TaskScreen>{
                        val args = it.toRoute<Routes.TaskScreen>()
                        TaskScreen(
                            navController = navController,
                            id = args.id
                        )
                    }
                }
            }
        }
    }
}