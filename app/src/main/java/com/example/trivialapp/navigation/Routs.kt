package com.example.trivialapp.navigation

sealed class Routes(val route: String) {
    object LunchScreen: Routes("lunchScreen?")
    object MenuScreen: Routes("menuScreen?")
    object GameScreen: Routes("gameScreen")
    object ResultScreen: Routes("resultScreen?{enhorabona}/{numInt}"){
        fun createRouteToResult(enhorabona: Boolean, numInt: Int) = "resultScreen?$enhorabona/$numInt"
    }
    object SettingsScreen: Routes("settingSreen?")
}