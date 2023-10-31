package com.example.notesmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesmvvm.screens.AddScreen
import com.example.notesmvvm.screens.MainScreen
import com.example.notesmvvm.screens.NoteScreen
import com.example.notesmvvm.screens.StartScreen
import com.example.notesmvvm.utils.Constants

sealed class Screens(val route: String) {
    object Start : Screens(route = Constants.Screens.START_SCREENS)
    object Main : Screens(route = Constants.Screens.MAIN_SCREENS)
    object Add : Screens(route = Constants.Screens.ADD_SCREENS)
    object Note : Screens(route = Constants.Screens.NOTE_SCREENS)
}

@Composable
fun NotesNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Start.route) {
        composable(route = Screens.Start.route) {
            StartScreen(navHostController = navController)
        }
        composable(route = Screens.Main.route) {
            MainScreen(navHostController = navController)
        }
        composable(route = Screens.Add.route) {
            AddScreen(navHostController = navController)
        }
        composable(route = Screens.Note.route) {
            NoteScreen(navHostController = navController)
        }
    }
}