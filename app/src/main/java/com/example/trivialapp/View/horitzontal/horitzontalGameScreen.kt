package com.example.trivialapp.View.horitzontal
import android.media.MediaPlayer
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.trivialapp.View.AnswersButtons
import com.example.trivialapp.View.TopBar
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.model.WindowInfo
import com.example.trivialapp.model.restarGame
import com.example.trivialapp.model.restartRound
import com.example.trivialapp.navigation.Routes

@Composable
fun HoritzontalGameScreen(navigationController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo, arrAudio: Array<MediaPlayer>) {
    val materialColor = MaterialTheme.colorScheme
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (turnBack, round, imgQuestions, question, answer, progres, nextQuestion) = createRefs()
        Button(
            onClick = {
                navigationController.navigate(Routes.MenuScreen.route)
                restarGame(settingsVM = settingsVM, gameVM = gameVM)
            },
            colors = ButtonDefaults.outlinedButtonColors(materialColor.secondary),
            modifier = Modifier.constrainAs(turnBack) {
                start.linkTo(parent.start, margin = 20.dp)
                top.linkTo(parent.top, margin = 20.dp)
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Turn back icon.",
                tint = materialColor.background
            )
        }
        Text(
            text = gameVM.randomQuestion.question,
            color = materialColor.primary,
            fontWeight = FontWeight.Bold,
            fontSize = settingsVM.textSize.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier
                .width(500.dp)
                .constrainAs(question) {
                top.linkTo(parent.top, margin = 10.dp)
                bottom.linkTo(imgQuestions.top)
                start.linkTo(turnBack.start)
                end.linkTo(round.end)
            }
        )
        Text(
            text = "Round ${gameVM.roundCount}/${settingsVM.rounds}",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            fontSize = settingsVM.textSize.sp,
            modifier = Modifier
                .padding(10.dp)
                .drawBehind {
                    drawCircle(
                        color = materialColor.secondary,
                        radius = this.size.height
                    )
                }
                .constrainAs(round) {
                    top.linkTo(question.top)
                    start.linkTo(question.end)
                }
        )
        Image(
            painter = painterResource(id = gameVM.randomQuestion.img),
            contentDescription = "Image question",
            modifier = Modifier
                .size(225.dp)
                .constrainAs(imgQuestions) {
                    top.linkTo(question.bottom)
                    bottom.linkTo(progres.top)
                    start.linkTo(parent.start)
                    end.linkTo(answer.start)
                }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(answer) {
                    top.linkTo(question.bottom)
                    bottom.linkTo(progres.top)
                    start.linkTo(question.end, margin = 86.dp)
                    end.linkTo(parent.end)
                }
        ) {
            AnswersButtons(settingsVM = settingsVM, gameVM = gameVM, windowInfo = windowInfo, arrAudio)
        }
        if (gameVM.stop) {
            Button(
                onClick = { restartRound(settingsVM = settingsVM, gameVM = gameVM) },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = materialColor.secondary,
                    contentColor = materialColor.background,
                ),
                modifier = Modifier.constrainAs(nextQuestion) {
                    top.linkTo(imgQuestions.bottom)
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(progres.start)
                }
            ) {
                Text(text = "Next question")
            }
        }
        LinearProgressIndicator(
            progress = gameVM.timePassed.toFloat() / settingsVM.time.toFloat(),
            color = materialColor.secondary,
            trackColor = materialColor.onSurface,
            modifier = Modifier
                .width(370.dp)
                .constrainAs(progres) {
                    top.linkTo(answer.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, margin = 50.dp)
                    end.linkTo(parent.end, margin = 150.dp)
                }
        )
    }
}
