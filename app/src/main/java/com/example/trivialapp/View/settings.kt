package com.example.trivialapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.trivialapp.ViewModel.MyViewModel
import com.example.trivialapp.model.Settings
import com.example.trivialapp.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navigationController: NavHostController, viewModel: MyViewModel) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()){
        val (difficulty, rounds,time,darkMode, returnMenue) = createRefs()
        val startGuide = createGuidelineFromStart(0.1f)
        val endGuide = createGuidelineFromEnd(0.1f)
        //var selectedDifficulty by remember { mutableStateOf("") }
        var expanded by remember { mutableStateOf(false) }
        val difficulties = arrayOf("EASY", "NORMAL", "DIFFICULT")
        val arrRounds = arrayOf(5, 10 ,15)
        //var selectedRounds by remember { mutableStateOf(0) }
        var arrStatus by rememberSaveable { mutableStateOf(arrayOf(false, false, false)) }
        var sliderValue by remember { mutableStateOf(0f) }
        var finishValue by remember { mutableStateOf("") }
        var timeCount by remember { mutableStateOf(10f) }
        //val settings by remember { mutableStateOf(Settings(difficulties[0], arrRounds[0], finishValue.toInt())) }
        Column (
            modifier = Modifier.constrainAs(difficulty) {
                top.linkTo(parent.top)
                bottom.linkTo(rounds.top)
                start.linkTo(rounds.start)
            }
        ) {
            Row {
                Text(text = "Difficulty", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(16.dp))
                OutlinedTextField(
                    value = viewModel.difficulty,
                    onValueChange = { viewModel.changeDifficulty(viewModel.difficulty) },
                    label = { Text("DIFFICULTY") },
                    enabled = false,
                    readOnly = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                    ),
                    modifier = Modifier
                        .clickable { expanded = true }
                        .fillMaxWidth(0.6f)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                difficulties.forEach { difficulty ->
                    DropdownMenuItem(text = { Text(text = difficulty) }, onClick = {
                        expanded = false
                        viewModel.changeDifficulty(difficulty)
                    })
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
            Text(text = "Rounds", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(16.dp))
            Column {
                arrRounds.indices.forEach { index ->
                    Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                        RadioButton(
                            selected = arrStatus[index],
                            onClick = {
                                arrStatus[index] = !arrStatus[index]
                                if (arrStatus[0] == true) viewModel.changeRouunds(arrRounds[0])
                                else if (arrStatus[1] == true) viewModel.changeRouunds(arrRounds[1])
                                else viewModel.changeRouunds(arrRounds[2])
                                      },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.secondary,
                                unselectedColor = MaterialTheme.colorScheme.tertiary
                            )
                        )
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(
                            text = "${arrRounds[index]}",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
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
                    bottom.linkTo(darkMode.top)
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
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Slider(
                value = timeCount,
                onValueChange = { timeCount = it },
                onValueChangeFinished = { viewModel.changeTime(sliderValue.toInt())},
                valueRange = 1f..10f,
                steps = 8,
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.tertiary
                )
            )
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
                modifier = Modifier.align(Alignment.CenterVertically)
                )
            Spacer(modifier = Modifier.padding(16.dp))
            Switch(checked = viewModel.darkThem, onCheckedChange = {viewModel.changeDarkThem(!viewModel.darkThem)})
        }
        Button(
            onClick = {navigationController.navigate(Routes.MenuScreen.route)},
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
            modifier = Modifier.constrainAs(returnMenue){
                top.linkTo(darkMode.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(startGuide)
                end.linkTo(endGuide)

            }
        ) {
            Text(text = "Return to menu")
        }
    }

}
