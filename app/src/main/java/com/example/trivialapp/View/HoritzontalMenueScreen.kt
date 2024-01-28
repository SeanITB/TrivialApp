package com.example.trivialapp.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trivialapp.R
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.model.WindowInfo
import com.example.trivialapp.model.restarGame
import com.example.trivialapp.navigation.Routes

@Composable
fun HoritzontalMenueScreen(navController: NavHostController, settingsVM: SettingsViewModel, gameVM: GameViewModel, windowInfo: WindowInfo){
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(painter = painterResource(
            id = if (!settingsVM.darkThem)
                R.drawable.trivial_icon4_l
            else
                R.drawable.trivial_icon4_n
        ),
            contentDescription = "logo",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Button(
                onClick = {
                    restarGame(settingsVM,gameVM)
                    navController.navigate(Routes.GameScreen.route)
                },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .width(200.dp)
            ) {
                Text(text = "New Game", fontSize = settingsVM.textSize.sp)
            }
            Button(
                onClick = { navController.navigate(Routes.SettingsScreen.route) },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Settings", fontSize = settingsVM.textSize.sp)
            }
        }
    }
}