package com.example.trivialapp.model

enum class Questions(
    val question: String,
    val img: Int,
    val answers: Array<String>
)

data class Settings (
    val difficulty: String,
    val rounds: Int,
    val time: Int,
    //val darkMode: Boolean
)

