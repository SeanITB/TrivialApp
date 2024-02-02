package com.example.trivialapp.ViewModel

import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.ui.platform.LocalContext


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

    var expanded by mutableStateOf(false)
        private set

    //val context = LocalContext.current
    //val audioManager = context.getSystemService(AUDIO_SERVICE) as AudioManager


    fun changeDarkThem(value: Boolean) {
        this.darkThem = value
    }

    fun changeDifficulty(value: String) {
        this.difficulty = value
    }

    fun changeRouunds(value: Int) {
        this.rounds = value
    }

    fun changeTime(value: Int) {
        this.time = value
    }

    fun changeTextSize(valeu: Int) {
        this.textSize = valeu
    }

    fun changeExpanded (value: Boolean) {
        this.expanded = value
    }


}
