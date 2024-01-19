package com.example.trivialapp.model

import androidx.compose.runtime.Composable

@Composable
fun rememberGameInfo(): GameInfo{
    return GameInfo(
        0,
        false,
        0,
        1,
        arrayOf(true, true, true, true),
        arrayOf(false, false, false, false),
        0.0f,
        Question.values().random()
    )
}

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