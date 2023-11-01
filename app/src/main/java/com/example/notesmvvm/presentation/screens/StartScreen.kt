package com.example.notesmvvm.presentation.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.notesmvvm.presentation.MainViewModel
import com.example.notesmvvm.presentation.MainViewModelFactory
import com.example.notesmvvm.presentation.navigation.Screens
import com.example.notesmvvm.utils.TYPE_FIREBASE
import com.example.notesmvvm.utils.TYPE_ROOM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Где хранить данные?")
            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_ROOM) {
                        navHostController.navigate(route = Screens.Main.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "На смартфоне")
            }
            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_FIREBASE) {
                        navHostController.navigate(route = Screens.Main.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "На облачном хранилище", textAlign = TextAlign.Center)
            }
        }
    }
}