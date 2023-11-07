package com.example.notesmvvm.utils

import com.example.notesmvvm.data.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository

object Constants {

    object Screens {
        const val START_SCREENS = "start_screens"
        const val MAIN_SCREENS = "main_screens"
        const val ADD_SCREENS = "add_screens"
        const val NOTE_SCREENS = "note_screens"
    }

    object Keys {
        const val NOTES_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "Добавить новую заметку"
        const val ADD_NOTE = "Добавить заметку"
        const val NOTE_TITLE = "Заголовок"
        const val NOTE_SUBTITLE = "Текст заметки"
        const val WHERE_IS_THE_DATA_STORED = "Где хранить данные?"
        const val ROOM_DATABASE = "На смартфоне"
        const val FIREBASE_DATABASE = "На облачном хранилище"
        const val ID = "id"
        const val NONE = "Пусто"
        const val UPDATE = "Обновить"
        const val DELETE = "Удалить"
        const val NAV_BACK = "Назад"
        const val EDIT_NOTE = "Введите текс"
        const val EMPTY = ""
        const val UPDATE_SUBTITLE = "Обновить заметку"
    }


}