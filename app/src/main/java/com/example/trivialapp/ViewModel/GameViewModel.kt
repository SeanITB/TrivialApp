package com.example.trivialapp.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.trivialapp.model.DifficultQuestions
import com.example.trivialapp.model.EasyQuestions
import com.example.trivialapp.model.NormalQuestions

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

    var textSize by mutableStateOf(20)
        private set

    var timePassed by mutableStateOf(0.0f)
        private set

    var randomEasyQuestions by mutableStateOf(EasyQuestions.values().random())
        private set

    var randomNormalQuestions by mutableStateOf(NormalQuestions.values().random())
        private set

    var randomDifficultQuestion by mutableStateOf(DifficultQuestions.values().random())
        private set

    var timeAnimation by mutableStateOf(0)
        private set

    var activeAnimation by mutableStateOf(false)
        private set
    var animationDone by mutableStateOf(false)
        private set


    fun updateUserAnswear(value: Int) {
        userAnswear = value
    }

    fun updateChek(value: Boolean) {
        check = value
    }

    fun updateRightAnsers(value: Int) {
        if (value == 0) roundCount = value
        rightAnswers += value
    }

    fun updateRoundCount(value: Int) {
        if (value == 0) roundCount = value
        else roundCount += value
    }

    fun updateTimePass(value: Float){
        if (value == 0.0f) timePassed = value
        else timePassed += value
    }

    fun updateEasyRandomQuestion(value: EasyQuestions) {
        randomEasyQuestions = value
    }

    fun updateNormalRandomQuestion(value: NormalQuestions) {
        randomNormalQuestions = value
    }

    fun updateDifficultRandomQuestion(value: DifficultQuestions) {
        randomDifficultQuestion = value
    }

    fun updateTextSize(value: Int) {
        textSize = value
    }

    fun updateTimeAnimation(value: Int) {
        timeAnimation += value
    }

    fun updateActiveAnimation(value: Boolean) {
        activeAnimation = value
    }

    fun updateAnimationDone(value: Boolean) {
        animationDone = value
    }
}