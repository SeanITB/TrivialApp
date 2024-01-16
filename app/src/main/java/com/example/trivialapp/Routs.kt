package com.example.trivialapp

import androidx.compose.ui.graphics.painter.Painter

sealed class Routes(val route: String) {
    object LunchScreen:Routes("lunchScreen?") {
    }
    object MenuScreen:Routes("menuScreen?") {

    }
    object GameScreen:Routes("gameScreen?{dificultad}"){
        fun createRouteToGame(dificultad: String?) = "gameScreen?$dificultad"
    }
    object ResultScreen:Routes("resultScreen?{dificultad}/{enhorabona}/{numInt}"){
        fun createRouteToResult(dificultad: String?, enhorabona: Boolean, numInt: Int) = "resultScreen?$dificultad/$enhorabona/$numInt"
    }
}