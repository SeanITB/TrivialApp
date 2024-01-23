package com.example.trivialapp.View

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.model.WindowInfo
import com.example.trivialapp.model.remeberWindowInfo
import com.example.trivialapp.model.restarGame
import com.example.trivialapp.navigation.Routes

@Composable
fun ResultScreen(navController: NavHostController, settingVM: SettingsViewModel, gameVM: GameViewModel){
    val windowInfo = remeberWindowInfo()
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ){
        val (msgInicial, msgSecundari, msgPA, imgPenjat, msgErrades, botTJ, botMenu) = createRefs()
        val topGuide = createGuidelineFromTop(0.1f)
        val bottomGuide = createGuidelineFromBottom(0.2f)
        Text(
            text = "Result of the game",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.constrainAs(msgInicial) {
                top.linkTo(topGuide)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Text(
            text = """
                Difficulty mode: ${settingVM.difficulty}
                Right answears: ${gameVM.rightAnswers}
                Number of round: ${settingVM.rounds}
                Time for round: ${settingVM.time*10} seconds
            """.trimIndent(),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.constrainAs(msgSecundari) {
                top.linkTo(msgInicial.bottom)
                bottom.linkTo(botTJ.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Button(
            onClick = {
                restarGame(settingVM, gameVM)
                navController.navigate(Routes.GameScreen.route)
                      },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .width(200.dp)
                .constrainAs(botTJ) {
                    top.linkTo(msgPA.top)
                    bottom.linkTo(bottomGuide)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(text = "Juga un altre cop")
        }
        Button(
            onClick = { navController.navigate(Routes.MenuScreen.route) },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .width(200.dp)
                .constrainAs(botMenu) {
                    top.linkTo(botTJ.bottom, margin = 15.dp)
                    start.linkTo(botTJ.start)
                }
        ) {
            Text(text = "Return to menu")
        }
    }
}