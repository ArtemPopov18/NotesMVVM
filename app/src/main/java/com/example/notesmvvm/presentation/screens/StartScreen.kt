package com.example.notesmvvm.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notesmvvm.presentation.MainViewModel
import com.example.notesmvvm.presentation.navigation.Screens
import com.example.notesmvvm.utils.Constants
import com.example.notesmvvm.utils.LOGIN
import com.example.notesmvvm.utils.PASSWORD
import com.example.notesmvvm.utils.TYPE_FIREBASE
import com.example.notesmvvm.utils.TYPE_ROOM
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navHostController: NavHostController, viewModel: MainViewModel) {

    val coroutineScope = rememberCoroutineScope()
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(true)
    var login by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var password by remember { mutableStateOf(Constants.Keys.PASSWORD_EMPTY) }

    if (openBottomSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { openBottomSheet = false },
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = Constants.Keys.LOG_IN,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = login,
                    onValueChange = { login = it },
                    label = { Text(text = Constants.Keys.LOGIN_TEXT) },
                    isError = login.isEmpty()
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = Constants.Keys.PASSWORD_TEXT) },
                    isError = login.isEmpty()
                )
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        LOGIN = login
                        PASSWORD = password
                        Log.d("рхранит", "$LOGIN $PASSWORD")
                        viewModel.initDatabase(TYPE_FIREBASE) {
                            navHostController.navigate(route = Screens.Main.route)
                        }
                    },
                    enabled = login.isNotEmpty() && password.isNotEmpty()
                ) {
                    Text(text = Constants.Keys.SIGN_IN)
                }
            })
    }

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
            Text(text = Constants.Keys.WHERE_IS_THE_DATA_STORED)
            Button(
                onClick = {
                    viewModel.initDatabase(TYPE_ROOM) {
                        navHostController.navigate(route = Screens.Main.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = Constants.Keys.ROOM_DATABASE)
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        bottomSheetState.show()
                    }
                    openBottomSheet = true
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = Constants.Keys.FIREBASE_DATABASE, textAlign = TextAlign.Center)
            }
        }
    }
}