
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
fun HoritzontalGameScreen(navigationController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (turnBack, imgQuestions, question, answer, progres, nextQuestion) = createRefs()
        /*
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            //TopBar(navigationController = navigationController, settingsVM = settingsVM, gameVM = gameVM)
            Spacer(modifier = Modifier.width(100.dp))
            Text(
                text = "Round ${gameVM.roundCount}/${settingsVM.rounds}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = settingsVM.textSize.sp,
                modifier = Modifier.padding(10.dp)
            )
        }

         */
        Button(
            onClick = {
                navigationController.navigate(Routes.MenuScreen.route)
                restarGame(settingsVM = settingsVM, gameVM = gameVM)
            },
            colors = ButtonDefaults.outlinedButtonColors(MaterialTheme.colorScheme.secondary),
            modifier = Modifier.constrainAs(turnBack) {
                start.linkTo(parent.start, margin = 20.dp)
                top.linkTo(parent.top, margin = 20.dp)
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Turn back icon.",
                tint = MaterialTheme.colorScheme.background
            )
        }
        Text(
            text = gameVM.randomQuestion.question,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = settingsVM.textSize.sp,
            maxLines = 2,
            modifier = Modifier.constrainAs(question) {
                top.linkTo(parent.top)
                bottom.linkTo(imgQuestions.top)
                start.linkTo(turnBack.start)
                end.linkTo(parent.end)
            }
        )
        Image(
            painter = painterResource(id = gameVM.randomQuestion.img),
            contentDescription = "Image question",
            modifier = Modifier
                .size(200.dp)
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
            AnswersButtons(settingsVM = settingsVM, gameVM = gameVM, windowInfo = windowInfo)
        }
        LinearProgressIndicator(
            progress = (gameVM.timePassed.toFloat() * settingsVM.time.toFloat())/100f/1f,
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier
                .width(370.dp)
                .constrainAs(progres) {
                    top.linkTo(answer.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end, margin = 150.dp)
                }
        )
        if (gameVM.stop) {
            Button(
                onClick = { restartRound(settingsVM = settingsVM, gameVM = gameVM) },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.background,
                ),
                modifier = Modifier.constrainAs(nextQuestion) {
                    top.linkTo(imgQuestions.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(progres.start)
                }
            ) {
                Text(text = "Next question")
            }
        }
    }
}
