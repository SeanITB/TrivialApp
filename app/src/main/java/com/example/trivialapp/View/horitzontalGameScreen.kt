
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@Composable
fun HoritzontalGameScreen(navigationController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (topBar, imgQuestion, question, answer, progres) = createRefs()
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
            TopBar(navigationController = navigationController, settingsVM = settingsVM, gameVM = gameVM)
            Spacer(modifier = Modifier.width(100.dp))
            Text(
                text = "Round ${if (gameVM.roundCount < settingsVM.rounds) gameVM.roundCount else gameVM.roundCount-1}/${settingsVM.rounds}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
        Image(
            painter = painterResource(id = gameVM.randomEasyQuestions.img),
            contentDescription = "Image question",
            modifier = Modifier
                .size(200.dp)
                .constrainAs(imgQuestion) {
                    top.linkTo(topBar.bottom)
                    bottom.linkTo(progres.top)
                    start.linkTo(parent.start)
                    end.linkTo(answer.start)
                }
        )
        Text(
            text = "¿Qué y para qué sirve?",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(question) {
                top.linkTo(topBar.bottom)
                bottom.linkTo(answer.top)
                start.linkTo(imgQuestion.end)
                end.linkTo(parent.end)
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
            AnswersButtons(gameVM = gameVM, windowInfo = windowInfo)
        }
        //Text(text = "${gameInfo.timePassed}")
        LinearProgressIndicator(
            progress = gameVM.timePassed/1f,
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