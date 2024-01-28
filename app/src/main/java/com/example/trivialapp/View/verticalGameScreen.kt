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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.model.WindowInfo
import com.example.trivialapp.model.restartRound

@Composable
fun VerticalGameScreen(navigationController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val startGuide = createGuidelineFromStart(0.1f)
        val endGuide = createGuidelineFromEnd(0.1f)
        val (diffText, round, imgQuestion, question, answer, nextQuestion, progres) = createRefs()
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
            TopBar(navigationController = navigationController, settingsVM = settingsVM, gameVM = gameVM)
        }
        Text(
            text = "Round ${gameVM.roundCount}/${settingsVM.rounds}",
            fontSize = settingsVM.textSize.sp,
            modifier = Modifier.constrainAs(round) {
                top.linkTo(diffText.bottom)
                bottom.linkTo(imgQuestion.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Image(
            painter = painterResource(id = gameVM.randomQuestion.img),
            contentDescription = "Image question",
            modifier = Modifier
                .size(300.dp)
                .constrainAs(imgQuestion) {
                    top.linkTo(round.bottom)
                    bottom.linkTo(question.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = gameVM.randomQuestion.question,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = settingsVM.textSize.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(question) {
                    top.linkTo(imgQuestion.bottom)
                    bottom.linkTo(answer.top, margin = 20.dp)
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
                    bottom.linkTo(progres.top, margin = 70.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            AnswersButtons(settingsVM = settingsVM, gameVM = gameVM, windowInfo = windowInfo)
        }
        if (gameVM.stop) {
            Button(
                onClick = { restartRound(settingsVM = settingsVM, gameVM = gameVM) },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.background,
                ),
                modifier = Modifier.constrainAs(nextQuestion) {
                    top.linkTo(answer.bottom)
                    bottom.linkTo(progres.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                Text(text = "Next question")
            }
        }
        LinearProgressIndicator(
            progress = (gameVM.timePassed.toFloat() * settingsVM.time.toFloat())/100f/1f,
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



