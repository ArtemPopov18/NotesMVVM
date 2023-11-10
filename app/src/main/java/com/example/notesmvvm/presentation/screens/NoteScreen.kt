package com.example.notesmvvm.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notesmvvm.model.Note
import com.example.notesmvvm.presentation.MainViewModel
import com.example.notesmvvm.presentation.navigation.Screens
import com.example.notesmvvm.utils.BD_TYPE
import com.example.notesmvvm.utils.Constants
import com.example.notesmvvm.utils.TYPE_FIREBASE
import com.example.notesmvvm.utils.TYPE_ROOM
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(navHostController: NavHostController, viewModel: MainViewModel, noteId: String?) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = when (BD_TYPE.value) {
        TYPE_FIREBASE -> {
            notes.firstOrNull { it.firebaseId == noteId } ?: Note()
        }

        TYPE_ROOM -> {
            notes.firstOrNull { it.id == noteId?.toInt() } ?: Note()
        }

        else -> Note()
    }
    val coroutineScope = rememberCoroutineScope()
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(true)
    var title by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var subtitle by remember { mutableStateOf(Constants.Keys.NONE) }

    if (openBottomSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { openBottomSheet = false },
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = Constants.Keys.EDIT_NOTE,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = Constants.Keys.NOTE_TITLE) },
                    isError = title.isEmpty()
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = subtitle,
                    onValueChange = { subtitle = it },
                    label = { Text(text = Constants.Keys.NOTE_SUBTITLE) },
                    isError = title.isEmpty()
                )
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        viewModel.updateNote(
                            note = Note(
                                id = note.id,
                                title = title,
                                subtitle = subtitle,
                                firebaseId = note.firebaseId
                            )
                        ) {
                            openBottomSheet = false
                            navHostController.navigate(route = Constants.Screens.MAIN_SCREENS) {
                                popUpTo(route = Screens.Main.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }) {
                    Text(text = Constants.Keys.UPDATE_SUBTITLE)
                }
            })
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .padding(22.dp),
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    text = note.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    text = note.subtitle,
                    fontSize = 18.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .padding(horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(modifier = Modifier.fillMaxWidth(0.18f),
                    onClick = {
                        navHostController.navigate(route = Constants.Screens.MAIN_SCREENS) {
                            popUpTo(route = Constants.Screens.MAIN_SCREENS) {
                                inclusive = true
                            }
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = Constants.Keys.NAV_BACK,
                        tint = Color.White
                    )
                }
                Button(
                    onClick = {
                        viewModel.deleteNote(note = note) {
                            navHostController.navigate(route = Constants.Screens.MAIN_SCREENS) {
                                popUpTo(route = Constants.Screens.MAIN_SCREENS) {
                                    inclusive = true
                                }
                            }
                        }
                    }) {
                    Text(text = Constants.Keys.DELETE)
                }
                Button(
                    onClick = {
                        coroutineScope.launch {
                            title = note.title
                            subtitle = note.subtitle
                            bottomSheetState.show()
                        }
                        openBottomSheet = true
                    }) {
                    Text(text = Constants.Keys.UPDATE)
                }
            }
        }
    }
}