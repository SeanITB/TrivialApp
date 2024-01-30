package com.example.trivialapp.View

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.model.WindowInfo
import com.example.trivialapp.navigation.Routes
import java.time.format.TextStyle

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController, settingsVM: SettingsViewModel, windowInfo: WindowInfo) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()){
        val (difficulty, rounds,time, volume, darkMode, returnMenue) = createRefs()
        val topGuide = createGuidelineFromTop(0.1f)
        val startGuide = createGuidelineFromStart(0.1f)
        val endGuide = createGuidelineFromEnd(0.1f)
        val options = arrayOf("SPORTS", "GENERAL KNOWLEDGE", "HISTORY")
        val arrRounds = arrayOf(5, 10 ,15)
        var sliderValueText by remember { mutableStateOf(settingsVM.textSize.toFloat()) }
        var finishValueTime by remember { mutableStateOf(settingsVM.time.toFloat()) }
        Column (
            modifier = Modifier.constrainAs(difficulty) {
                top.linkTo(topGuide)
                bottom.linkTo(rounds.top)
                start.linkTo(rounds.start)
            }
        ) {
            Row {
                Text(text = """
                    PLAY 
                    MODE
                """.trimIndent(), fontWeight = FontWeight.Bold, fontSize = settingsVM.textSize.sp)
                Spacer(modifier = Modifier.padding(16.dp))
                Box(
                    //contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth(0.95f)
                ) {
                    OutlinedTextField(
                        value = settingsVM.difficulty,
                        onValueChange = { settingsVM.changeDifficulty(settingsVM.difficulty) },
                        label = { Text("PLAY MODE", color = MaterialTheme.colorScheme.primary) },
                        placeholder = {
                                      Text(
                                          text = "", style = androidx.compose.ui.text.TextStyle(
                                              color = MaterialTheme.colorScheme.primary,
                                          )
                                      )
                        },
                        enabled = false,
                        readOnly = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            cursorColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .clickable { settingsVM.changeExpanded(true) }
                            .fillMaxWidth(0.6f)
                    )
                    DropdownMenu(
                        expanded = settingsVM.expanded,
                        onDismissRequest = { settingsVM.changeExpanded(false) },
                        modifier = Modifier.background(MaterialTheme.colorScheme.secondary)
                    ) {
                        options.forEach { difficulty ->
                            DropdownMenuItem(
                                text = { Text(text = difficulty, color = MaterialTheme.colorScheme.background) },
                                onClick = {
                                    settingsVM.changeExpanded(false)
                                    settingsVM.changeDifficulty(difficulty)
                                }
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .constrainAs(rounds) {
                    top.linkTo(difficulty.bottom)
                    bottom.linkTo(time.top)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                }
        ) {
            Text(text = "Rounds", fontWeight = FontWeight.Bold, fontSize = settingsVM.textSize.sp)
            Spacer(modifier = Modifier.padding(16.dp))
            Column {
                arrRounds.forEach { round ->
                    Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                        RadioButton(
                            selected = round == settingsVM.rounds,
                            onClick = { settingsVM.changeRouunds(round) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.secondary,
                                unselectedColor = MaterialTheme.colorScheme.tertiary
                            )
                        )
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(
                            text = "$round",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = settingsVM.textSize.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .constrainAs(time) {
                    top.linkTo(rounds.bottom)
                    bottom.linkTo(volume.top)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                }
        ) {
            Text(
                text = """
                Time per 
                round
            """.trimIndent(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = settingsVM.textSize.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Slider(
                    value = finishValueTime,
                    onValueChange = { finishValueTime = it },
                    onValueChangeFinished = { settingsVM.changeTime(finishValueTime.toInt())},
                    valueRange = 10f..30f,
                    steps = 18,
                    modifier = Modifier.fillMaxWidth(),
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.secondary,
                        activeTrackColor = MaterialTheme.colorScheme.secondary,
                        inactiveTrackColor = MaterialTheme.colorScheme.tertiary
                    )
                )
                Text(text = "${settingsVM.time}", fontSize = settingsVM.textSize.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .constrainAs(volume) {
                    top.linkTo(time.bottom)
                    bottom.linkTo(darkMode.top)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                }
        ) {
            Text(
                text = """
                Text
                size
            """.trimIndent(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = settingsVM.textSize.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Slider(
                    value = sliderValueText,
                    onValueChange = { sliderValueText = it},
                    onValueChangeFinished = { settingsVM.changeTextSize(sliderValueText.toInt())},
                    valueRange = 15f..25f,
                    steps = 18,
                    modifier = Modifier.fillMaxWidth(),
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.secondary,
                        activeTrackColor = MaterialTheme.colorScheme.secondary,
                        inactiveTrackColor = MaterialTheme.colorScheme.tertiary
                    )
                )
                Text(text = "${settingsVM.textSize}", fontSize = settingsVM.textSize.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(MaterialTheme.colorScheme.background)
                .constrainAs(darkMode) {
                    top.linkTo(time.bottom)
                    bottom.linkTo(returnMenue.top)
                    start.linkTo(rounds.start)
                }
        ) {
            Text(
                text = "Dark Mode",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = settingsVM.textSize.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Switch(checked = settingsVM.darkThem, onCheckedChange = {settingsVM.changeDarkThem(!settingsVM.darkThem)})
        }
        Button(
            onClick = {navController.navigate(Routes.MenuScreen.route)},
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
            modifier = Modifier.constrainAs(returnMenue){
                top.linkTo(darkMode.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(startGuide)
                end.linkTo(endGuide)

            }
        ) {
            Text(text = "Save and return", fontSize = settingsVM.textSize.sp)
        }
    }
}
