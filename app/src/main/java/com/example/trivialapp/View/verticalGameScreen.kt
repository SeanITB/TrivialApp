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
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.MyViewModel
import com.example.trivialapp.navigation.Routes
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import com.example.trivialapp.model.GameInfo
import com.example.trivialapp.model.Question
import com.example.trivialapp.model.WindowInfo
import com.example.trivialapp.model.remeberWindowInfo
import com.example.trivialapp.model.rememberGameInfo

@Composable
fun GameScreen(navigationController: NavHostController, viewModel: MyViewModel) {
    val windowInfo = remeberWindowInfo()
    val gameInfo = rememberGameInfo()
    if (gameInfo.timePassed >= 1f || false in gameInfo.enabledButtons) {
        gameInfo.randomQuestion = Question.values().random()
        gameInfo.timePassed = 0.0f
        gameInfo.roundCount++
        for (index in gameInfo.enabledButtons.indices) gameInfo.enabledButtons[index] = true
    }
    if (windowInfo.sreenWidthInfo is WindowInfo.WindowType.Compact)
        VerticalGameScreen(navigationController, viewModel, gameInfo)
    else
        HoritzontalGameScreen(navigationController, viewModel, gameInfo)
    
    if (gameInfo.roundCount < viewModel.rounds + 1) {
        if (gameInfo.check == true) {
            if (gameInfo.randomQuestion.answers[gameInfo.userAnswear].equals(gameInfo.randomQuestion.raightAnswear)) {
                gameInfo.correctAnswers[gameInfo.userAnswear] = true
                gameInfo.rightAnswers += 1
            }
            gameInfo.check = false
        }
    } else
        navigationController.navigate(Routes.ResultScreen.route)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun VerticalGameScreen(navigationController: NavHostController, viewModel: MyViewModel, gameInfo: GameInfo) {
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
                text = "${viewModel.difficulty} MODE",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Text(
            text = "Round ${if (gameInfo.roundCount < viewModel.rounds) gameInfo.roundCount else gameInfo.roundCount-1}/${viewModel.rounds}",
            modifier = Modifier.constrainAs(round) {
                top.linkTo(diffText.bottom)
                bottom.linkTo(imgQuestion.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Image(
            painter = painterResource(id = gameInfo.randomQuestion.img),
            contentDescription = "animació del penjat",
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
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(answer) {
                    top.linkTo(question.bottom)
                    bottom.linkTo(progres.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            gameInfo.randomQuestion.answers.indices.forEach { index ->
                Button(
                    onClick = {
                        gameInfo.check = true
                        gameInfo.userAnswear = index
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
                    ),

                    ) {
                    Text(
                        text = gameInfo.randomQuestion.answers[index],
                        color = MaterialTheme.colorScheme.background,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
        LaunchedEffect(key1 = gameInfo.timePassed) {
            if (gameInfo.timePassed < 1f) {
                delay(1000L)
                gameInfo.timePassed += 0.05f
            }
        }

        Text(text = "${gameInfo.timePassed}")
        LinearProgressIndicator(
            progress = gameInfo.timePassed/1f,
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
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HoritzontalGameScreen(navigationController: NavHostController, viewModel: MyViewModel, gameInfo: GameInfo) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
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
                text = "${viewModel.difficulty} MODE",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Text(
            text = "Round ${if (gameInfo.roundCount < viewModel.rounds) gameInfo.roundCount else gameInfo.roundCount-1}/${viewModel.rounds}",
            modifier = Modifier.constrainAs(round) {
                top.linkTo(diffText.bottom)
                bottom.linkTo(imgQuestion.top)
                start.linkTo(parent.start)
                end.linkTo(question.start)
            }
        )
        Image(
            painter = painterResource(id = gameInfo.randomQuestion.img),
            contentDescription = "animació del penjat",
            modifier = Modifier
                .size(200.dp)
                .constrainAs(imgQuestion) {
                    top.linkTo(round.bottom)
                    bottom.linkTo(question.top)
                    start.linkTo(parent.start)
                    end.linkTo(question.start)
                }
        )
        Text(
            text = "¿Qué y para qué sirve?",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(question) {
                top.linkTo(question.bottom)
                bottom.linkTo(progres.top)
                start.linkTo(parent.start)
                end.linkTo(question.start)
            }
        )
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(answer) {
                    top.linkTo(parent.top)
                    bottom.linkTo(progres.top)
                    start.linkTo(imgQuestion.end)
                    end.linkTo(parent.end)
                }
        ) {
            gameInfo.randomQuestion.answers.indices.forEach { index ->
                Button(
                    onClick = {
                        gameInfo.check = true
                        gameInfo.userAnswear = index
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
                    ),

                    ) {
                    Text(
                        text = gameInfo.randomQuestion.answers[index],
                        color = MaterialTheme.colorScheme.background,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
        LaunchedEffect(key1 = gameInfo.timePassed) {
            if (gameInfo.timePassed < 1f) {
                delay(1000L)
                gameInfo.timePassed += 0.05f
            }
        }

        //Text(text = "${gameInfo.timePassed}")
        LinearProgressIndicator(
            progress = gameInfo.timePassed/1f,
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
}
