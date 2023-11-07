package com.example.notesmvvm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesmvvm.presentation.MainViewModel
import com.example.notesmvvm.presentation.screens.AddScreen
import com.example.notesmvvm.presentation.screens.MainScreen
import com.example.notesmvvm.presentation.screens.NoteScreen
import com.example.notesmvvm.presentation.screens.StartScreen
import com.example.notesmvvm.utils.Constants

sealed class Screens(val route: String) {
    object Start : Screens(route = Constants.Screens.START_SCREENS)
    object Main : Screens(route = Constants.Screens.MAIN_SCREENS)
    object Add : Screens(route = Constants.Screens.ADD_SCREENS)
    object Note : Screens(route = Constants.Screens.NOTE_SCREENS)
}

@Composable
fun NotesNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Start.route) {
        composable(route = Screens.Start.route) {
            StartScreen(navHostController = navController, viewModel = mViewModel)
        }
        composable(route = Screens.Main.route) {
            MainScreen(navHostController = navController, viewModel = mViewModel)
        }
        composable(route = Screens.Add.route) {
            AddScreen(navHostController = navController, viewModel = mViewModel)
        }
        composable(route = Screens.Note.route + "/{${Constants.Keys.ID}}") {
            NoteScreen(navHostController = navController, viewModel = mViewModel, noteId = it.arguments?.getString(Constants.Keys.ID))
        }
    }
}