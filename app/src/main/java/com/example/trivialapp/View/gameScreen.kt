package com.example.trivialapp.View

import android.util.Log
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun GameScreen(navigationController: NavHostController, viewModel: MyViewModel) {
    var lletraUsuari by remember { mutableStateOf("") }
    var comprovar by remember { mutableStateOf(false) }
    var congratulations by remember { mutableStateOf(false) }
    val maxErrades: Int = 10
    var numErrades by remember { mutableStateOf(0) }
    var roundCount by remember { mutableStateOf(1) }
    var progresCount by remember { mutableStateOf(0.1f) }
    val answers = arrayOf("a", "b", "c", "f")
    val arrQuestions = arrayOf(
        arrayOf("ddd", "iii", "ooo"),
        arrayOf("ddddd", "iiiii", "ooooo"),
        arrayOf("ddddddd", "iiiiiii", "ooooooo")
    )
    val enabledButtons = arrayOf(true, true, true, true)
    val correctAnswers = arrayOf(false, false, false, false)
    //Crear paraula aleatoria
    val diffInt by remember { mutableStateOf(
        when (viewModel.difficulty) {
            "EASY" ->  0
            "NORMAL" -> 1
            else -> 2
        }
    ) }
    val randomQuestion by remember { mutableStateOf(arrQuestions[diffInt].random()) }
    ConstraintLayout (
        modifier = Modifier.fillMaxWidth()
    ) {
        val startGuide = createGuidelineFromStart(0.1f)
        val endGuide = createGuidelineFromEnd(0.1f)
        val (diffText, round, question, answer, progres) = createRefs()
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
                text = "MODE ${viewModel.difficulty}",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Text(
            text = "Round $roundCount/${viewModel.rounds}",
            modifier = Modifier.constrainAs(round) {
                top.linkTo(diffText.bottom)
                bottom.linkTo(question.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            )
        //Text(text = paraulaAleatoria)
        /* podira poner imagenes por cada pregunta
        Image(
            painter = painterResource(id = penjatPng[numErrades]),
            contentDescription = "animació del penjat",
            modifier = Modifier
                .size(200.dp)
                .constrainAs(hagmna){
                    top.linkTo(dificultatText.bottom)
                    bottom.linkTo(lletresSelecioneades.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

         */
        Text(
            text = randomQuestion,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.constrainAs(question) {
                top.linkTo(round.bottom)
                bottom.linkTo(answer.top)
                start.linkTo(startGuide)
                end.linkTo(endGuide)
            }
            )
        FlowRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .width(160.dp)
                .constrainAs(answer) {
                    top.linkTo(question.bottom)
                    bottom.linkTo(progres.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            answers.indices.forEach{ index ->
                Button(
                    onClick = { comprovar = true },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.background,
                        disabledContainerColor =
                        if (correctAnswers[index])
                            Color.Green
                        else
                            Color.Red,
                        disabledContentColor = Color.Black
                    ),
                    enabled = enabledButtons[index],
                ) {
                    Text(
                        text = answers[index],
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
        LaunchedEffect(key1 = viewModel.time) {
            while (viewModel.time > 0) {
                delay(1000L)
                viewModel.changeTime(viewModel.time - 1)
            }
        }
        LinearProgressIndicator(
            progress = progresCount,
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
    Log.i("Rounds","$roundCount")
    if (roundCount < 10) {
        if (comprovar == true) {
            roundCount++
            comprovar = false
        }
    } else
        navigationController.navigate(
            Routes.ResultScreen.createRouteToResult(
                enhorabona = congratulations,
                numInt = numErrades
            )
        )


}