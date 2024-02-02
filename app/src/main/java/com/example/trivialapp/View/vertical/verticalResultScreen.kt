package com.example.trivialapp.View.vertical

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.trivialapp.R
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.model.WindowInfo
import com.example.trivialapp.model.restarGame
import com.example.trivialapp.navigation.Routes

@Composable
fun VerticalResultScreen(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo){
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ){
        val (initialMsg, icon, secundaryMsg, butPA, butRG, share) = createRefs()
        val bottomGuide = createGuidelineFromBottom(0.2f)
        val context = LocalContext.current
        val sentIntent = Intent(Intent.ACTION_SEND).apply {
            type = "msg/palin"
            putExtra(Intent.EXTRA_TEXT, "My game result")
        }
        val shareIntent = Intent.createChooser(sentIntent, "Share with...")
        Text(
            text = "Result of the game",
            fontWeight = FontWeight.Bold,
            fontSize = (settingsVM.textSize+10).sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.constrainAs(initialMsg) {
                top.linkTo(parent.top)
                bottom.linkTo(icon.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Image(painter = painterResource(
            id = if (!settingsVM.darkThem)
                R.drawable.trivial_icon4_l
            else
                R.drawable.trivial_icon4_n
        ),
            contentDescription = "logo",
            modifier = Modifier
                .size(200.dp)
                .constrainAs(icon) {
                    top.linkTo(initialMsg.bottom)
                    bottom.linkTo(secundaryMsg.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = """
                Difficulty mode: ${settingsVM.difficulty}
                Right answers: ${gameVM.rightAnswers}
                Number of round: ${settingsVM.rounds}
                Total time: ${gameVM.totalTime} seconds
            """.trimIndent(),
            fontWeight = FontWeight.Bold,
            fontSize = settingsVM.textSize.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.constrainAs(secundaryMsg) {
                top.linkTo(icon.bottom)
                bottom.linkTo(butPA.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Button(
            onClick = {
                restarGame(settingsVM, gameVM)
                navController.navigate(Routes.GameScreen.route)
            },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .width(200.dp)
                .constrainAs(butPA) {
                    top.linkTo(secundaryMsg.bottom)
                    bottom.linkTo(bottomGuide)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(text = "Play Again", fontSize = settingsVM.textSize.sp)
        }
        Button(
            onClick = { navController.navigate(Routes.MenuScreen.route) },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .width(200.dp)
                .constrainAs(butRG) {
                    top.linkTo(butPA.bottom, margin = 15.dp)
                    //bottom.linkTo(share.top)
                    start.linkTo(butPA.start)
                }
        ) {
            Text(text = "Return to menu", fontSize = settingsVM.textSize.sp, textAlign = TextAlign.Center)
        }
        Button(
            onClick = {
                ContextCompat.startActivity(context, shareIntent, null)
            },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .width(200.dp)
                .constrainAs(share) {
                    top.linkTo(butRG.bottom, margin = 15.dp)
                    //bottom.linkTo(parent.bottom)
                    start.linkTo(butPA.start)
                }
        ) {
            Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
            Text(text = "Shere", fontSize = settingsVM.textSize.sp)
        }
    }
}
