package com.example.notesmvvm.presentation.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.notesmvvm.model.Note
import com.example.notesmvvm.presentation.MainViewModel
import com.example.notesmvvm.presentation.MainViewModelFactory
import com.example.notesmvvm.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            navHostController.navigate(route = Screens.Add.route)
        }) {
            Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null)
        }
    }) {
//        LazyColumn{
//            items(notes){note ->
//                NoteItem(paddingValues = it, navHostController = navHostController, note = note)
//            }
//        }
    }
}

@Composable
fun NoteItem(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    note: Note
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable { navHostController.navigate(route = Screens.Note.route) },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Text(
            text = note.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = note.subtitle
        )
    }
}