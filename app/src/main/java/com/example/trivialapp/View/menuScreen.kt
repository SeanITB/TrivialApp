package com.example.trivialapp.View

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.trivialapp.View.horitzontal.HoritzontalMenueScreen
import com.example.trivialapp.View.vertical.VerticalMenueScreen
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.model.WindowInfo

@Composable
fun MenuScreen(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo){
    if (windowInfo.sreenWidthInfo is WindowInfo.WindowType.Compact)
        VerticalMenueScreen(navController, settingsVM, gameVM, windowInfo)
    else
        HoritzontalMenueScreen(navController, settingsVM, gameVM, windowInfo)


}