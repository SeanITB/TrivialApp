package com.example.trivialapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.MyViewModel
import com.example.trivialapp.navigation.Routes
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import com.example.trivialapp.model.Question

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class, ExperimentalStdlibApi::class)
@Composable
fun HoritzontalGameScreen(navigationController: NavHostController, viewModel: MyViewModel) {
    var userAnswear by remember { mutableStateOf(0) }
    var comprovar by remember { mutableStateOf(false) }
    var numRaightAnswars by remember { mutableStateOf(0) }
    var roundCount by remember { mutableStateOf(1) }
    var actualRoundCount by remember { mutableStateOf(1) }
    val enabledButtons = arrayOf(true, true, true, true)
    val correctAnswers = arrayOf(false, false, false, false)
    var timePast by remember { mutableStateOf(0.0f) }
    var randomQuestion by remember { mutableStateOf(Question.values().random()) }
    if (timePast >= 1f || roundCount != actualRoundCount) {
        randomQuestion = Question.values().random()
        timePast = 0.0f
        actualRoundCount += 1
    }
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
            Button(
                onClick = { navigationController.navigate(Routes.MenuScreen.route) },
                colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Turn back icon.",
                    tint = Color.White
                )
            }
            Text(
                text = "${viewModel.difficulty} MODE Horitzontal",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Text(
            text = "Round ${if (roundCount < viewModel.rounds) roundCount else roundCount-1}/${viewModel.rounds}",
            modifier = Modifier.constrainAs(round) {
                top.linkTo(diffText.bottom)
                bottom.linkTo(imgQuestion.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Image(
            painter = painterResource(id = randomQuestion.img),
            contentDescription = "animació del penjat",
            modifier = Modifier
                .size(200.dp)
                .constrainAs(imgQuestion){
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
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                //.width(160.dp)
                .fillMaxWidth()
                .constrainAs(answer) {
                    top.linkTo(question.bottom)
                    bottom.linkTo(progres.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            randomQuestion.answers.indices.forEach { index ->
                Button(
                    onClick = {
                        comprovar = true
                        userAnswear = index
                        enabledButtons[index] = false
                              },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.width(350.dp),
                    enabled = enabledButtons[index],
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.background,
                        disabledContainerColor =
                        if (correctAnswers[index])
                            Color.Green
                        else
                            Color.Red,
                        disabledContentColor = MaterialTheme.colorScheme.primary
                    ),

                ) {
                    Text(
                        text = randomQuestion.answers[index],
                        color = MaterialTheme.colorScheme.background,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
        LaunchedEffect(key1 = timePast) {
            if (timePast < 1f) {
                delay(1000L)
                timePast += 0.05f
            }
            //while (timePast < viewModel.time) { }
        }
        Text(text = "$timePast")
        LinearProgressIndicator(
            progress = timePast/1f,
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier
                .width(370.dp)
                .constrainAs(progres) {
                    top.linkTo(answer.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
    if (roundCount < viewModel.rounds + 1) {
        if (comprovar == true) {
            if (randomQuestion.answers[userAnswear].equals(randomQuestion.raightAnswear)) {
                correctAnswers[userAnswear] = true
                numRaightAnswars += 1
            }
            roundCount++
            comprovar = false
        }
    } else
        navigationController.navigate(Routes.ResultScreen.route)
}
