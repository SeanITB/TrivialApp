package com.example.trivialapp.model

import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.navigation.Routes

fun restartRound(gameInfo: GameViewModel) {
    gameInfo.updateRandomQuestion(easyQuestions.values().random())
    gameInfo.updateTimePass(0.0f)
    gameInfo.updateRoundCount(1)
    for (index in gameInfo.enabledButtons.indices) {
        gameInfo.enabledButtons[index] = true
        gameInfo.correctAnswers[index] = false
    }
    gameInfo.updateNextQuestion(false)
}

fun checkAnswer(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel) {
    if (gameVM.roundCount < settingsVM.rounds + 1) {
        if (gameVM.check == true) {
            if (
                //gameVM.animationDone == false &&
                gameVM.randomEasyQuestions.answers[gameVM.userAnswear].equals(gameVM.randomEasyQuestions.raightAnswear)
                ) {
                //gameVM.updateActiveAnimation(true)
                gameVM.correctAnswers[gameVM.userAnswear] = true
                gameVM.updateRightAnsers(1)
            }
            //if (gameVM.animationDone == true) {
                gameVM.updateChek(false)
                //gameVM.updateActiveAnimation(false)
                //gameVM.updateAnimationDone(false)
                gameVM.updateNextQuestion(true)
            //}
        }
    } else
        navController.navigate(Routes.ResultScreen.route)
}

fun restarGame(gameVM: GameViewModel) {
    gameVM.updateUserAnswear(0)
    gameVM.updateChek(false)
    gameVM.updateRightAnsers(0)
    gameVM.updateRoundCount(0)
    for (index in gameVM.enabledButtons.indices){
        gameVM.enabledButtons[index] = true
        gameVM.correctAnswers[index] = false
    }
    gameVM.updateTimePass(0.0f)
    gameVM.updateRandomQuestion(easyQuestions.values().random())
}