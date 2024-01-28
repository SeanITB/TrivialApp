package com.example.trivialapp.navigation

sealed class Routes(val route: String) {
    object LunchScreen: Routes("lunchScreen")
    object MenuScreen: Routes("menuScreen")
    object GameScreen: Routes("gameSreen")
    object ResultScreen: Routes("resultScreen")
    object SettingsScreen: Routes("settingSreen?")
}