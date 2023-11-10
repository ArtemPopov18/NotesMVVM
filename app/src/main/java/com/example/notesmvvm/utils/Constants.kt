package com.example.notesmvvm.utils

import com.example.notesmvvm.data.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
const val FIREBASE_ID = "firebaseId"

lateinit var REPOSITORY: DatabaseRepository
lateinit var LOGIN: String
lateinit var PASSWORD: String
lateinit var BD_TYPE: String

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
        const val NOTE_TITLE = "title"
        const val NOTE_SUBTITLE = "subtitle"
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
        const val SIGN_IN = "Войти"
        const val LOG_IN = "Авторизоваться"
        const val LOGIN_TEXT = "Email"
        const val PASSWORD_TEXT = "Пароль"
        const val PASSWORD_EMPTY = ""
    }


}