package com.example.trivialapp.View

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.trivialapp.View.horitzontal.HoritzontalResultSreen
import com.example.trivialapp.View.vertical.VerticalResultScreen
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.model.WindowInfo

@Composable
fun ResultScreen(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo){
    if (windowInfo.sreenWidthInfo is WindowInfo.WindowType.Compact)
        VerticalResultScreen(navController, settingsVM, gameVM, windowInfo)
    else
        HoritzontalResultSreen(navController, settingsVM, gameVM)
}