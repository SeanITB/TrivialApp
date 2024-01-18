package com.example.trivialapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var darkThem by mutableStateOf(false)
        private set

    var difficulty by mutableStateOf("EASY")
        private set

    var rounds by mutableStateOf(10)
        private set

    var time by mutableStateOf(10)
        private set

    fun changeDarkThem(value: Boolean) {
        darkThem = value
    }

    fun changeDifficulty(value: String) {
        difficulty = value
    }

    fun changeRouunds(value: Int) {
        rounds = value
    }

    fun changeTime(value: Int) {
        time = value
    }

}
