package com.example.notesmvvm.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesmvvm.data.room.AppRoomDatabase
import com.example.notesmvvm.data.room.repository.RoomRepository
import com.example.notesmvvm.model.Note
import com.example.notesmvvm.utils.REPOSITORY
import com.example.notesmvvm.utils.TYPE_FIREBASE
import com.example.notesmvvm.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun readAllNotes() = REPOSITORY.readAll
}