package com.example.trivialapp.navigation

sealed class Routes(val route: String) {
    object LunchScreen: Routes("lunchScreen")
    object MenuScreen: Routes("menuScreen")
    object VerticalGameScreen: Routes("verticalGameScreen")
    object HoritzontalGameScreen: Routes("horitzontalGameScreen")
    object ResultScreen: Routes("resultScreen")
    object SettingsScreen: Routes("settingSreen?")
}