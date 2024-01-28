package com.example.trivialapp.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.trivialapp.R
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.model.WindowInfo
import com.example.trivialapp.model.restarGame
import com.example.trivialapp.navigation.Routes

@Composable
fun VerticalMenueScreen(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo){
    Column (modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ){
            val (icon, play, settings) = createRefs()
            val topGuide = createGuidelineFromTop(0.2f)
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
                        top.linkTo(topGuide)
                        bottom.linkTo(play.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Button(
                onClick = {
                    restarGame(settingsVM,gameVM)
                    navController.navigate(Routes.GameScreen.route)
                },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .width(200.dp)
                    .constrainAs(play) {
                        top.linkTo(icon.bottom, margin = 35.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(text = "New Game", fontSize = settingsVM.textSize.sp)
            }
            Button(
                onClick = { navController.navigate(Routes.SettingsScreen.route) },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .width(200.dp)
                    .constrainAs(settings) {
                        top.linkTo(play.bottom, margin = 5.dp)
                        start.linkTo(play.start)
                    }
            ) {
                Text(text = "Settings", fontSize = settingsVM.textSize.sp)
            }
        }
    }
}