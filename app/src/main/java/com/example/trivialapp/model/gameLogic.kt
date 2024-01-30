package com.example.trivialapp.model

import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.*
import com.example.trivialapp.navigation.Routes

fun generateRandomQuestion(settingsVM: SettingsViewModel): Question{
    val easyQuestions: MutableList<Question> = mutableListOf()
    val normalQuestions: MutableList<Question> = mutableListOf()
    val difficultQuesstions: MutableList<Question> = mutableListOf()

    // Classifies the questions for if they are not played and for the difficulty.
    for (question in Question.values()) {
        if (question.isDone == false) {
            when (question.difficulty) {
                'S' -> easyQuestions.add(question)
                'G' -> normalQuestions.add(question)
                else -> difficultQuesstions.add(question)
            }
        }
    }

    // Es guarda la pregunta
    val selectedQuestion = when (settingsVM.difficulty) {
        "SPORTS" -> easyQuestions.random()
        "GENERAL KNOWLEDGE" -> normalQuestions.random()
        else -> difficultQuesstions.random()
    }
    return selectedQuestion
}
fun restartRound(settingsVM: SettingsViewModel, gameVM: GameViewModel) {
    gameVM.updateRandomQuestion(generateRandomQuestion(settingsVM))
    gameVM.updateTimePass(0)
    gameVM.updateRoundCount(1)
    for (index in gameVM.enabledButtons.indices) {
        gameVM.enabledButtons[index] = true
        gameVM.correctAnswers[index] = false
    }
    gameVM.updateNextQuestion(false)
    gameVM.updateStop(false)
    gameVM.updateTimeAnimation(0)
}

fun checkAnswer(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel) {
    if (gameVM.roundCount < settingsVM.rounds + 1) {
        if (gameVM.check == true) {
            gameVM.randomQuestion.isDone = true
            gameVM.updateChek(false)
            gameVM.updateStop(true)
            gameVM.updateTotalTime(gameVM.timePassed)
            if (gameVM.randomQuestion.answers[gameVM.userAnswear].equals(gameVM.randomQuestion.raightAnswer)) {
                gameVM.correctAnswers[gameVM.userAnswear] = true
                gameVM.updateRightAnsers(1)
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
        gameVM.enabledButtons[index] = true
        gameVM.correctAnswers[index] = false
    }
    gameVM.updateTimePass(0)
    gameVM.updateRandomQuestion(generateRandomQuestion(settingsVM))
}