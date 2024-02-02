package com.example.trivialapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.trivialapp.model.Question

class GameViewModel: ViewModel(){

    var userAnswear by mutableStateOf(0)
        private set

    var check by mutableStateOf(false)
        private set

    var rightAnswers by mutableStateOf(0)
        private set

    var roundCount by mutableStateOf(1)
        private set

    var enabledButtons by mutableStateOf(arrayOf(true, true, true, true))
        private set

    var correctAnswers by mutableStateOf(arrayOf(false, false, false, false))
        private set

    var timePassed by mutableStateOf(0)
        private set

    var randomQuestion by mutableStateOf(Question.values().random())
        private set

    var timeAnimation by mutableStateOf(0)
        private set

    var stop by mutableStateOf(false)
        private set

    var nextQuestion by mutableStateOf(false)
        private set

    var totalTime by mutableStateOf(0)
        private set



    fun updateUserAnswear(value: Int) {
        this.userAnswear = value
    }

    fun updateChek(value: Boolean) {
        this.check = value
    }

    fun updateRightAnsers(value: Int) {
        if (value == 0) this.rightAnswers = value
        this.rightAnswers += value
    }

    fun updateRoundCount(value: Int) {
        if (value == 0) this.roundCount = 1
        else this.roundCount += value
    }

    fun updateTimePass(value: Int){
        if (value == 0) this.timePassed = value
        else this.timePassed += value
    }


    fun updateRandomQuestion(value: Question) {
        this.randomQuestion = value
    }

    fun updateTimeAnimation(value: Int) {
        if (value == 0) this.timeAnimation = value
        else this.timeAnimation += value
    }


    fun updateStop(value: Boolean) {
        stop = value
    }


    fun updateNextQuestion(value: Boolean) {
        this.nextQuestion = value
    }

    fun updateTotalTime(value: Int){
        if (value == 0) this.totalTime = value
        else this.totalTime += value
    }
}