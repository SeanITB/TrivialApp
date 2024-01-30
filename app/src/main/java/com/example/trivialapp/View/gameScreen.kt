package com.example.trivialapp.View

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import com.example.trivialapp.View.horitzontal.HoritzontalGameScreen
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
import com.example.trivialapp.View.vertical.VerticalGameScreen
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.navigation.Routes
import kotlinx.coroutines.delay
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.model.WindowInfo
import com.example.trivialapp.model.checkAnswer
import com.example.trivialapp.model.restarGame
import com.example.trivialapp.model.restartRound

@Composable
fun GameScreen(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo) {

    if (gameVM.timePassed >= settingsVM.time) {
        restartRound(settingsVM = settingsVM, gameVM = gameVM)
    }

    LaunchedEffect(key1 = gameVM.timePassed) {
        if (gameVM.timePassed < settingsVM.time && !gameVM.stop) {
            delay(1000L)
            gameVM.updateTimePass(1)
        }
    }

    if (windowInfo.sreenWidthInfo is WindowInfo.WindowType.Compact)
        VerticalGameScreen(navController, settingsVM, gameVM, windowInfo)
    else
        HoritzontalGameScreen(navController, settingsVM, gameVM, windowInfo)
    
    checkAnswer(navController = navController, settingsVM = settingsVM, gameVM = gameVM)
}


@Composable
fun TopBar(navigationController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel) {
    Button(
        onClick = {
            navigationController.navigate(Routes.MenuScreen.route)
            restarGame(settingsVM = settingsVM, gameVM = gameVM)
                  },
        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Turn back icon.",
            tint = MaterialTheme.colorScheme.background
        )
    }
    Text(
        text = "${settingsVM.difficulty} MODE",
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.background,
        fontSize = 20.sp,
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun AnswersButtons (settingsVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo) {
    gameVM.randomQuestion.answers.indices.forEach { index ->
        val animatedColorRight =
            animateColorAsState(
                MaterialTheme.colorScheme.onTertiary,
                animationSpec = tween(1000, easing = LinearEasing),
                label = "Color animation"
            )
        val animatedColorWrong =
            animateColorAsState(
                if(!gameVM.correctAnswers[index]) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.secondary,
                animationSpec = tween(1000, easing = LinearEasing),
                label = "Color animation"
            )
        Button(
            onClick = {
                gameVM.updateChek(true)
                gameVM.updateUserAnswear(index)
                gameVM.enabledButtons[index] = false
            },
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.width(350.dp),
            enabled = gameVM.enabledButtons[index],
            colors = ButtonDefaults.buttonColors(
                containerColor = if (gameVM.stop && gameVM.randomQuestion.answers[index].equals(gameVM.randomQuestion.raightAnswer)) animatedColorRight.value else MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = if(gameVM.correctAnswers[index]) animatedColorRight.value else animatedColorWrong.value,
                disabledContentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = gameVM.randomQuestion.answers[index],
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = settingsVM.textSize.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            if (windowInfo.sreenWidthInfo is WindowInfo.WindowType.Compact)
                Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

