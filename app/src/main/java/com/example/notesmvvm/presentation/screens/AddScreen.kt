package com.example.notesmvvm.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notesmvvm.model.Note
import com.example.notesmvvm.presentation.MainViewModel
import com.example.notesmvvm.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navHostController: NavHostController, viewModel: MainViewModel) {
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Добавить заметку",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                },
                label = { Text(text = "Заголовок") },
                isError = title.isEmpty()
            )

            OutlinedTextField(
                value = subtitle,
                onValueChange = {
                    subtitle = it
                    isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                },
                label = { Text(text = "Текст заметки") },
                isError = subtitle.isEmpty()
            )

            Button(
                modifier = Modifier.padding(top = 16.dp),
                enabled = isButtonEnabled,
                onClick = {
                    viewModel.addNote(note = Note(title = title, subtitle = subtitle)) {
                        navHostController.navigate(route = Screens.Main.route)
                    }
                }) {
                Text(text = "Добавить заметку")
            }
        }
    }
}