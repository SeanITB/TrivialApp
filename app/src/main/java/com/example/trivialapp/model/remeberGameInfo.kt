package com.example.trivialapp.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
/*
@Composable
fun rememberGameInfo(): GameInfo{
    return GameInfo(
        userAnswear = 0,
        check = false,
        rightAnswers = 0,
        roundCount = 1,
        enabledButtons = arrayOf(true, true, true, true),
        correctAnswers = arrayOf(false, false, false, false),
        timePassed = 0.0f,
        randomQuestion = Question.values().random()
    )
}

 */

class RememberGameInfo: ViewModel(){
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

    var timePassed by mutableStateOf(0.0f)
        private set

    var randomQuestion by mutableStateOf(Question.values().random())
        private set

    //toDo: ns muy bien pq, el recurso dice q es para visualizar, pero eso ya lo puedo hacer
    //val liveGameInfo = mutableListOf(gameInfo)

    fun updateUserAnswear(value: Int) {
        userAnswear = value
        //liveGameInfo.value = gameInfo
    }

    fun updateChek(value: Boolean) {
        check = value
    }

    fun updateRightAnsers(value: Int) {
        rightAnswers += value
    }

    fun updateRoundCount(value: Int) {
        roundCount += value
    }

    fun updateEnabledButtons(value: Boolean, index: Int){
        enabledButtons[index] = value
    }

    fun updateCorrectAnswears(value: Boolean, index: Int){
        correctAnswers[index] = value
    }

    fun updateTimePass(value: Float){
        if (value == 0.0f) timePassed = value
        else timePassed += value
    }


    fun updateRandomQuestion(value: Question) {
        randomQuestion = value
    }

}

/*
data class GameInfo (
    var userAnswear: Int,
    var check: Boolean,
    var rightAnswers: Int,
    var roundCount: Int,
    val enabledButtons: Array<Boolean>,
    val correctAnswers: Array<Boolean>,
    var timePassed: Float,
    var randomQuestion: Question
    )

 */