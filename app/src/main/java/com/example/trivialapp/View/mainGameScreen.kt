package com.example.trivialapp.View

import HoritzontalGameScreen
import android.widget.Toast
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.trivialapp.model.restarGame
import com.example.trivialapp.model.restartRound

@Composable
fun GameScreen(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel) {
    val windowInfo = remeberWindowInfo()

    //createQuestion(settingsVM, gameVM)

    if (gameVM.timePassed >= 1f || false in gameVM.enabledButtons) {
        restartRound(settingsVM = settingsVM, gameVM = gameVM)
    }

    LaunchedEffect(key1 = gameVM.timePassed) {
        if (gameVM.timePassed < 1f) {
            delay(1000L)
            gameVM.updateTimePass(0.05f)
        }
    }

    if (gameVM.activeAnimation)
        Animation(gameVM = gameVM)

    Text(text = "${gameVM.timePassed}")
    if (windowInfo.sreenWidthInfo is WindowInfo.WindowType.Compact)
        VerticalGameScreen(navController, settingsVM, gameVM, windowInfo)
    else
        HoritzontalGameScreen(navController, settingsVM, gameVM, windowInfo)
    
    checkAnswer(navController = navController, settingsVM = settingsVM, gameVM = gameVM)
}

// toDo: No esta entrado en el LunchEffect
@Composable
fun Animation(gameVM: GameViewModel){
    val context = LocalContext.current
    LaunchedEffect(key1 = gameVM.activeAnimation) {
        delay(2000L)
        Toast.makeText(context, "Funciona", Toast.LENGTH_LONG).show()
        if (gameVM.timeAnimation < 1) {
            gameVM.updateTimeAnimation(1)
        }
    }
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
fun AnswersButtons (gameVM: GameViewModel, windowInfo: WindowInfo) {
    gameVM.randomQuestion.answers.indices.forEach { index ->
        val isCeck by remember {
            mutableStateOf(gameVM.check)
        }
        val transition = updateTransition(
            targetState = isCeck,
            label = null
        )
        val color by transition.animateColor(
            transitionSpec = { tween(2000) },
            label = "Color button",
            targetValueByState = {isCeck ->
                if(isCeck)
                    Color.Green
                else
                    Color.Red
            }
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
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = color,
                disabledContentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = gameVM.randomQuestion.answers[index],
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


