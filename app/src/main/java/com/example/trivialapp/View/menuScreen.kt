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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.trivialapp.R
import com.example.trivialapp.ViewModel.MyViewModel
import com.example.trivialapp.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navigationController: NavHostController, viewModel: MyViewModel){
    val selectedText by remember { mutableStateOf("EASY") }
    Column (modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ){
            val (imgPenjat, opcions, jugar, ajuda) = createRefs()
            val topGuide = createGuidelineFromTop(0.2f)
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "logo",
                modifier = Modifier
                    .size(200.dp)
                    .constrainAs(imgPenjat) {
                        top.linkTo(topGuide)
                        bottom.linkTo(opcions.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Button(
                onClick = { navigationController.navigate(Routes.GameScreen.route) },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .width(200.dp)
                    .constrainAs(jugar) {
                        top.linkTo(opcions.bottom, margin = 35.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(text = "New Game")
            }
            Button(
                onClick = { navigationController.navigate(Routes.SettingsScreen.route) },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .width(200.dp)
                    .constrainAs(ajuda) {
                        top.linkTo(jugar.bottom, margin = 5.dp)
                        start.linkTo(jugar.start)
                    }
            ) {
                Text(text = "Settings")
            }
        }
    }
}