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
import com.example.notesmvvm.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navHostController: NavHostController) {
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
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
                onValueChange = { title = it },
                label = { Text(text = "Заголовок") })

            OutlinedTextField(
                value = subtitle,
                onValueChange = { subtitle = it },
                label = { Text(text = "Текст заметки") })

            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    navHostController.navigate(route = Screens.Main.route)
                }) {
                Text(text = "Добавить заметку")
            }
        }
    }
}