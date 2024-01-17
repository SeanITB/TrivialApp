package com.example.trivialapp.View

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.MyViewModel
import com.example.trivialapp.navigation.Routes

@Composable
fun ResultScreen(navController: NavHostController, myViewModel: MyViewModel, numInt: Int, enhorabona: Boolean){
    val arrMsg by remember { mutableStateOf(arrayOf("", "")) }
    if (enhorabona == true) {
        arrMsg[0] = "Enhorabona!"
        arrMsg[1] = "Has guanyat"
    }
    else {
        arrMsg[0] = "La meva sincera compassió"
        arrMsg[1] = "Has perdut"
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ){
        val (msgInicial, msgSecundari, msgPA, imgPenjat, msgErrades, botTJ, botMenu) = createRefs()
        val topGuide = createGuidelineFromTop(0.1f)
        val bottomGuide = createGuidelineFromBottom(0.2f)
        Text(
            text = arrMsg[0],
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.constrainAs(msgInicial) {
                top.linkTo(topGuide)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Button(
            onClick = { navController.navigate(Routes.GameScreen.route) },
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