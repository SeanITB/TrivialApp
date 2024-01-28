package com.example.trivialapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    var darkThem by mutableStateOf(false)
        private set

    var difficulty by mutableStateOf("SPORTS")
        private set

    var rounds by mutableStateOf(5)
        private set

    var time by mutableStateOf(20)
        private set

    var textSize by mutableStateOf(20)
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

    fun changeTextSize(valeu: Int) {
        textSize = valeu
    }

}
