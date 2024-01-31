package com.example.trivialapp.View.horitzontal

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.model.restarGame
import com.example.trivialapp.navigation.Routes

@Composable
fun HoritzontalResultSreen(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel){
    val context = LocalContext.current
    val sentIntent = Intent(Intent.ACTION_SEND).apply {
        type = "msg/palin"
        putExtra(Intent.EXTRA_TEXT, "My game result")
    }
    val shareIntent = Intent.createChooser(sentIntent, "Share with...")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Result of the game",
                fontWeight = FontWeight.Bold,
                fontSize = settingsVM.textSize.sp,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = """
                Difficulty mode: ${settingsVM.difficulty}
                Right answers: ${gameVM.rightAnswers}
                Number of round: ${settingsVM.rounds}
                Time for round: ${settingsVM.time} seconds
            """.trimIndent(),
                fontWeight = FontWeight.Bold,
                fontSize = settingsVM.textSize.sp,
                color = MaterialTheme.colorScheme.primary,

                )
        }
        Spacer(modifier = Modifier.width(50.dp))
        Column (
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ){
            Button(
                onClick = {
                    restarGame(settingsVM, gameVM)
                    navController.navigate(Routes.GameScreen.route)
                },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Play Again", fontSize = settingsVM.textSize.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { navController.navigate(Routes.MenuScreen.route) },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Return to menu", fontSize = settingsVM.textSize.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    ContextCompat.startActivity(context, shareIntent, null)
                },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier.width(200.dp)
            ) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                Text(text = "Shere", fontSize = settingsVM.textSize.sp)
            }
        }
    }
}