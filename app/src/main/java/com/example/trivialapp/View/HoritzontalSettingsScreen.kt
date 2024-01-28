package com.example.trivialapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoritzontalSettingsScreen(navController: NavHostController, settingsVM: SettingsViewModel) {
    val options = arrayOf("SPORTS", "GENERAL KNOWLEDGE", "HISTORY")
    val arrRounds = arrayOf(5, 10, 15)
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Text(
                    text = """
                    PLAY 
                    MODE
                """.trimIndent(), fontWeight = FontWeight.Bold, fontSize = settingsVM.textSize.sp
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Box(
                    //contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth(0.95f)
                ) {
                    OutlinedTextField(
                        value = settingsVM.difficulty,
                        onValueChange = { settingsVM.changeDifficulty(settingsVM.difficulty) },
                        label = { Text("PLAY MODE") },
                        //colors = MaterialTheme.colorScheme.primary, toDo: poner color de contraste
                        enabled = false,
                        readOnly = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                        ),
                        modifier = Modifier
                            .clickable { settingsVM.changeExpanded(true) }
                            .fillMaxWidth(0.6f)
                    )
                    DropdownMenu(
                        expanded = settingsVM.expanded,
                        onDismissRequest = { settingsVM.changeExpanded(false) },
                        modifier = Modifier
                    ) {
                        options.forEach { difficulty ->
                            DropdownMenuItem(text = { Text(text = difficulty) }, onClick = {
                                settingsVM.changeExpanded(false)
                                settingsVM.changeDifficulty(difficulty)
                            })
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Rounds", fontWeight = FontWeight.Bold, fontSize = settingsVM.textSize.sp)
            Spacer(modifier = Modifier.padding(16.dp))
            Column {
                arrRounds.forEach { round ->
                    Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                        RadioButton(
                            selected = round == settingsVM.selectedOption,
                            onClick = { settingsVM.changeSelectedOption(round) },
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
    }
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
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
            ) {
                Slider(
                    value = settingsVM.time.toFloat(),
                    onValueChange = { settingsVM.changeTime(it.toInt()) },
                    //onValueChangeFinished = { settingsVM.changeTime(finishValueTime.toInt())},
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
            modifier = Modifier.fillMaxWidth(0.8f)
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
                    value = settingsVM.textSize.toFloat(),
                    onValueChange = { settingsVM.changeTextSize(it.toInt()) },
                    //onValueChangeFinished = { settingsVM.changeTextSize(sliderValueText.toInt())},
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
        ) {
            Text(
                text = "Dark Mode",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = settingsVM.textSize.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Switch(
                checked = settingsVM.darkThem,
                onCheckedChange = { settingsVM.changeDarkThem(!settingsVM.darkThem) })
        }
    }

}