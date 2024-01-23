package com.example.trivialapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.SettingsViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.model.WindowInfo

@Composable
fun VerticalGameScreen(navigationController: NavHostController, settingVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val startGuide = createGuidelineFromStart(0.1f)
        val endGuide = createGuidelineFromEnd(0.1f)
        val (diffText, round, imgQuestion, question, answer, progres) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
                .constrainAs(diffText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            TopBar(navigationController = navigationController, settingsVM = settingVM, gameVM = gameVM)
        }
        Text(
            text = "Round ${if (gameVM.roundCount < settingVM.rounds+1) gameVM.roundCount else gameVM.roundCount-1}/${settingVM.rounds}",
            modifier = Modifier.constrainAs(round) {
                top.linkTo(diffText.bottom)
                bottom.linkTo(imgQuestion.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Image(
            painter = painterResource(id = gameVM.randomEasyQuestions.img),
            contentDescription = "Image question",
            modifier = Modifier
                .size(200.dp)
                .constrainAs(imgQuestion) {
                    top.linkTo(round.bottom)
                    bottom.linkTo(question.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = "¿Qué y para qué sirve?",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(question) {
                top.linkTo(question.bottom)
                bottom.linkTo(answer.top)
                start.linkTo(startGuide)
                end.linkTo(endGuide)
            }
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(answer) {
                    top.linkTo(question.bottom)
                    bottom.linkTo(progres.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            AnswersButtons(gameVM = gameVM, windowInfo = windowInfo)
        }
        LinearProgressIndicator(
            progress = gameVM.timePassed/1f,
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier
                .width(370.dp)
                .constrainAs(progres) {
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}



