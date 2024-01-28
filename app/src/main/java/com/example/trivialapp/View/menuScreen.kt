package com.example.trivialapp.View

import HoritzontalGameScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.trivialapp.R
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.model.WindowInfo
import com.example.trivialapp.model.remeberWindowInfo
import com.example.trivialapp.model.restarGame
import com.example.trivialapp.navigation.Routes

@Composable
fun MenuScreen(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo){

    if (windowInfo.sreenWidthInfo is WindowInfo.WindowType.Compact)
        VerticalMenueScreen(navController, settingsVM, gameVM, windowInfo)
    else
        HoritzontalMenueScreen(navController, settingsVM, gameVM, windowInfo)


}