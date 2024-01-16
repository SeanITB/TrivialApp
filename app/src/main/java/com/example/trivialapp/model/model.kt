package com.example.trivialapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var darkThem by mutableStateOf(false)
        private set

    fun changeDarkThem(value: Boolean) {
        darkThem = value
    }

}
