package com.example.todolist_revisionwithhilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.todolist_revisionwithhilt.RoomDB.ToDoDatabase
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

        val db by lazy{
            Room.databaseBuilder(
                context = applicationContext,
                ToDoDatabase::class.java,
                "RoomDB"
            ).build()
        }

        setContent {
            ToDoList_RevisionWithHiltTheme {
                val roomDao = db.dao
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.HomeScreen){
                    composable<Routes.HomeScreen>{
                        HomeScreen(
                            navController
                        )
                    }
                    composable<Routes.TaskScreen>{
                        TaskScreen(
                            dao = roomDao,
                            navController
                        )
                    }
                }
            }
        }
    }
}