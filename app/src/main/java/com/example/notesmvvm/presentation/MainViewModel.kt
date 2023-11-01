package com.example.notesmvvm.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.notesmvvm.data.room.AppRoomDatabase
import com.example.notesmvvm.data.room.repository.RoomRepository
import com.example.notesmvvm.model.Note
import com.example.notesmvvm.utils.REPOSITORY
import com.example.notesmvvm.utils.TYPE_FIREBASE
import com.example.notesmvvm.utils.TYPE_ROOM

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
}