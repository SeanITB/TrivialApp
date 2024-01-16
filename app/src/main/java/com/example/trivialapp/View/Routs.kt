package com.example.trivialapp.View

sealed class Routes(val route: String) {
    object LunchScreen: Routes("lunchScreen?")
    object MenuScreen: Routes("menuScreen?")
    object GameScreen: Routes("gameScreen?{dificultad}"){
        fun createRouteToGame(dificultad: String?) = "gameScreen?$dificultad"
    }
    object ResultScreen: Routes("resultScreen?{dificultad}/{enhorabona}"){
        fun createRouteToResult(dificultad: String?, enhorabona: Boolean) = "resultScreen?$dificultad/$enhorabona"
    }
    object SettingsScreen: Routes("settingSreen?")
}