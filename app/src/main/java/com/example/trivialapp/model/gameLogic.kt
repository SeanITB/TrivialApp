package com.example.trivialapp.model

import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.navigation.Routes

fun generateRandomQuestion(settingsVM: SettingsViewModel): Question{
    val easyQuestions: MutableList<Question> = mutableListOf()
    val normalQuestions: MutableList<Question> = mutableListOf()
    val difficultQuesstions: MutableList<Question> = mutableListOf()

    // Clasifica els valors segons la dificultad i si ja s'han fet
    for (question in Question.values()) {
        when (question.difficulty) {
            'E' -> {
                if (question.isDone == false)
                    easyQuestions.add(question)
            }
            'N' -> {
                if (question.isDone == false)
                    normalQuestions.add(question)
            }
            else -> {
                if (question.isDone == false)
                    difficultQuesstions.add(question)
            }
        }
    }

    // Es guarda la pregunta
    val selectedQuestion = when (settingsVM.difficulty) {
        "EASY" -> easyQuestions.random()
        "NORMAL" -> normalQuestions.random()
        else -> difficultQuesstions.random()
    }
    return selectedQuestion
}
fun restartRound(settingsVM: SettingsViewModel, gameVM: GameViewModel) {
    gameVM.updateRandomQuestion(generateRandomQuestion(settingsVM))
    gameVM.updateTimePass(0.0f)
    gameVM.updateRoundCount(1)
    for (index in gameVM.enabledButtons.indices) {
        gameVM.enabledButtons[index] = true
        gameVM.correctAnswers[index] = false
    }
    gameVM.updateNextQuestion(false)
    gameVM.updateActiveAnimation(false)
    gameVM.updateTimeAnimation(0)
}

fun checkAnswer(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel) {
    if (gameVM.roundCount < settingsVM.rounds + 1) {
        if (gameVM.check == true) {
            gameVM.randomQuestion.isDone = true
            gameVM.enabledButtons[gameVM.userAnswear] = false
            if (
                //gameVM.animationDone == false &&
                gameVM.randomQuestion.answers[gameVM.userAnswear].equals(gameVM.randomQuestion.raightAnswear)
                ) {
                //gameVM.updateActiveAnimation(true)
                gameVM.correctAnswers[gameVM.userAnswear] = true
                gameVM.updateRightAnsers(1)
            }
            //if (gameVM.animationDone == true) {
                gameVM.updateChek(false)
                gameVM.updateActiveAnimation(true)
                //gameVM.updateAnimationDone(false)
            //}
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
        gameVM.enabledButtons[index] = true
        gameVM.correctAnswers[index] = false
    }
    gameVM.updateTimePass(0.0f)
    gameVM.updateRandomQuestion(generateRandomQuestion(settingsVM))
}