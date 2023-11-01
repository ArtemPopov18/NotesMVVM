package com.example.notesmvvm.utils

import com.example.notesmvvm.data.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository

class Constants {

    object Screens {
        const val START_SCREENS = "start_screens"
        const val MAIN_SCREENS = "main_screens"
        const val ADD_SCREENS = "add_screens"
        const val NOTE_SCREENS = "note_screens"
    }


}