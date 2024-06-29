package com.example.todolist_revisionwithhilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.todolist_revisionwithhilt.RoomDB.RoomDatabase
import com.example.todolist_revisionwithhilt.util.Routes
import com.example.todolist_revisionwithhilt.ui.screens.HomeScreen.HomeScreen
import com.example.todolist_revisionwithhilt.ui.screens.TaskScreen.TaskScreen
import com.example.todolist_revisionwithhilt.ui.theme.ToDoList_RevisionWithHiltTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db by lazy{
            Room.databaseBuilder(
                context = applicationContext,
                RoomDatabase::class.java,
                "RoomDB"
            ).build()
        }

        setContent {
            ToDoList_RevisionWithHiltTheme {
                val roomDao = db.roomDao()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.HomeScreen){
                    composable<Routes.HomeScreen>{
                        HomeScreen(
                            navController,
                            dao = roomDao
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


//@Serializable
//object HomeScreen
//@Serializable
//object TaskScreen