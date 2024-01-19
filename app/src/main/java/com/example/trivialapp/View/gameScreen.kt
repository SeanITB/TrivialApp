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
import java.util.concurrent.TimeUnit
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import com.example.trivialapp.model.Questions

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class, ExperimentalStdlibApi::class)
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
    var timePast by remember { mutableStateOf(0.0f) }
    var randomQuestion by remember { mutableStateOf(Questions.values().random()) }
    if (timePast == 0.1f)
        randomQuestion = Questions.values().random()
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
            text = "Round $roundCount/${viewModel.rounds}",
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
            answers.indices.forEach { index ->
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
                        text = randomQuestion.answers[index],
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }

        LaunchedEffect(key1 = timePast) {
            if (timePast == viewModel.time) {
                timePast = 0.0f
            } else {
                delay(1000L)
                timePast+0.1f
            }
            //while (timePast < viewModel.time) { }
        }
        Text(text = "$timePast")
        LinearProgressIndicator(
            progress = timePast,
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

    Log.i("Rounds", "$roundCount")
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
    if (timePast == viewModel.time) timePast = 0.1f

}

private fun formatTime(time: Long): String {
    val seconds = TimeUnit.MILLISECONDS.toSeconds(time)
    return String.format("0.00", seconds)
}

private fun startTimer(context: android.content.Context, onTick: (Long) -> Unit) {
    val handler = Handler(Looper.getMainLooper())
    var elapsedTime = 0L

    handler.postDelayed(object : Runnable {
        override fun run() {
            elapsedTime += 100L // update every 100 milliseconds
            onTick(elapsedTime)
            handler.postDelayed(this, 100)
        }
    }, 100)
}