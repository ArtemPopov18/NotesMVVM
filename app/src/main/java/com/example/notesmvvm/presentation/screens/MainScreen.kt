package com.example.notesmvvm.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notesmvvm.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            navHostController.navigate(route = Screens.Add.route)
        }) {
            Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null)
        }
    }) {
        Column {
            NoteItem(paddingValues = it, navHostController = navHostController, title = "Title 1", subtitle = "Note 1")
            NoteItem(paddingValues = it, navHostController = navHostController, title = "Title 1", subtitle = "Note 1")
            NoteItem(paddingValues = it, navHostController = navHostController, title = "Title 1", subtitle = "Note 1")
        }
    }
}

@Composable
fun NoteItem(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    title: String,
    subtitle: String
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
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = subtitle
        )
    }
}