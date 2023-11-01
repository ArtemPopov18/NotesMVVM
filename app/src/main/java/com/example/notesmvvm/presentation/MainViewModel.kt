package com.example.notesmvvm.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.notesmvvm.model.Note
import com.example.notesmvvm.utils.TYPE_FIREBASE
import com.example.notesmvvm.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val readNote: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }

    init {
        readNote.value = when(dbType.value){
            TYPE_ROOM -> {
                listOf<Note>(
                    Note(title = "Note 1", subtitle = "subtitle 1"),
                    Note(title = "Note 2", subtitle = "subtitle 2"),
                    Note(title = "Note 3", subtitle = "subtitle 3"),
                    Note(title = "Note 6", subtitle = "subtitle 6")
                )
            }
            TYPE_FIREBASE -> listOf()
            else -> listOf()
        }
    }

    fun initDatabase(type: String) {
        dbType.value = type
    }
}