package com.example.trivialapp.model

import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.navigation.Routes

fun createQuestion(settingsVM: SettingsViewModel, gameVM: GameViewModel) {
    when (settingsVM.difficulty) {
        "EASY" -> gameVM.updateEasyRandomQuestion(EasyQuestions.values().random())
        "NORMAL" -> gameVM.updateNormalRandomQuestion(NormalQuestions.values().random())
        else -> gameVM.updateDifficultRandomQuestion(DifficultQuestions.values().random())
    }
}
fun restartRound(settingsVM: SettingsViewModel, gameInfo: GameViewModel) {
    createQuestion(settingsVM, gameInfo)
    gameInfo.updateTimePass(0.0f)
    gameInfo.updateRoundCount(1)
    for (index in gameInfo.enabledButtons.indices) gameInfo.enabledButtons[index] = true
}

fun checkAnswer(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel) {
    if (gameVM.roundCount < settingsVM.rounds + 1) {
        if (gameVM.check == true) {
            if (
                gameVM.animationDone == false &&
                gameVM.randomEasyQuestions.answers[gameVM.userAnswear].equals(gameVM.randomEasyQuestions.raightAnswear)
                ) {
                gameVM.updateActiveAnimation(true)
                gameVM.correctAnswers[gameVM.userAnswear] = true
                gameVM.updateRightAnsers(1)
            }
            if (gameVM.animationDone == true) {
                gameVM.updateChek(false)
                gameVM.updateActiveAnimation(false)
                gameVM.updateAnimationDone(false)
            }
        }
    } else
        navController.navigate(Routes.ResultScreen.route)
}

fun restarGame(settingsVM: SettingsViewModel, gameVM: GameViewModel) {
    gameVM.updateUserAnswear(0)
    gameVM.updateChek(false)
    gameVM.updateRightAnsers(0)
    gameVM.updateRoundCount(0)
    for (index in gameVM.enabledButtons.indices){
        gameVM.enabledButtons[index] = false
        gameVM.correctAnswers[index] = true
    }
    gameVM.updateTimePass(0.0f)
    createQuestion(settingsVM, gameVM)
}