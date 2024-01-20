package com.example.trivialapp.View

import HoritzontalGameScreen
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.navigation.Routes
import kotlinx.coroutines.delay
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.model.WindowInfo
import com.example.trivialapp.model.checkAnswer
import com.example.trivialapp.model.remeberWindowInfo
import com.example.trivialapp.model.restartRound

@Composable
fun GameScreen(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel) {
    val windowInfo = remeberWindowInfo()

    if (gameVM.timePassed >= 1f || false in gameVM.enabledButtons) {
        restartRound(gameInfo = gameVM)
    }

    LaunchedEffect(key1 = gameVM.timePassed) {
        if (gameVM.timePassed < 1f) {
            delay(1000L)
            gameVM.updateTimePass(0.05f)
        }
    }

    Text(text = "${gameVM.timePassed}")
    if (windowInfo.sreenWidthInfo is WindowInfo.WindowType.Compact)
        VerticalGameScreen(navController, settingsVM, gameVM, windowInfo)
    else
        HoritzontalGameScreen(navController, settingsVM, gameVM, windowInfo)
    
    checkAnswer(navController = navController, settingsVM = settingsVM, gameVM = gameVM)
}

@Composable
fun TopBar(navigationController: NavHostController, viewModel: SettingsViewModel) {
    Button(
        onClick = { navigationController.navigate(Routes.MenuScreen.route) },
        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Turn back icon.",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    Text(
        text = "${viewModel.difficulty} MODE",
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 20.sp,
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun AnswersButtons (gameInfo: GameViewModel, windowInfo: WindowInfo) {
    gameInfo.randomQuestion.answers.indices.forEach { index ->
        Button(
            onClick = {
                gameInfo.updateChek(true)
                gameInfo.updateUserAnswear(index)
                gameInfo.enabledButtons[index] = false
            },
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.width(350.dp),
            enabled = gameInfo.enabledButtons[index],
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.background,
                disabledContainerColor =
                if (gameInfo.correctAnswers[index])
                    Color.Green
                else
                    Color.Red,
                disabledContentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = gameInfo.randomQuestion.answers[index],
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            if (windowInfo.sreenWidthInfo is WindowInfo.WindowType.Compact)
                Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


