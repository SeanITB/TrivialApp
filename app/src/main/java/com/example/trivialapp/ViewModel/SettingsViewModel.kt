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

    var difficulty by mutableStateOf("EASY")
        private set

    var rounds by mutableStateOf(5)
        private set

    var time by mutableStateOf(2f)
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

    fun changeTime(value: Float) {
        time = value
    }

    /*
    fun getTextSize(): Float {
        val result: Float
        result = textSize.toString().toFloat()
        return result
    }

     */
    fun changeTextSize(valeu: Int) {
        textSize = valeu
    }

}
