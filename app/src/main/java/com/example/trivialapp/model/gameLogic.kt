package com.example.trivialapp.model

import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.navigation.Routes

fun restartRound(gameInfo: GameViewModel) {
    gameInfo.updateRandomQuestion(Question.values().random())
    gameInfo.updateTimePass(0.0f)
    gameInfo.updateRoundCount(1)
    for (index in gameInfo.enabledButtons.indices) gameInfo.enabledButtons[index] = true
}

fun checkAnswer(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel) {
    if (gameVM.roundCount < settingsVM.rounds + 1) {
        if (gameVM.check == true) {
            if (gameVM.randomQuestion.answers[gameVM.userAnswear].equals(gameVM.randomQuestion.raightAnswear)) {
                gameVM.correctAnswers[gameVM.userAnswear] = true
                gameVM.updateRightAnsers(1)
            }
            gameVM.updateChek(false)
        }
    } else
        navController.navigate(Routes.ResultScreen.route)
}