package com.example.trivialapp.View

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.trivialapp.model.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navigationController: NavHostController, VIEW_MODEL: MyViewModel) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()){
        val (difficulty, rounds,time,darkMode, returnMenue) = createRefs()
        var selectedText by remember { mutableStateOf("") }
        var expanded by remember { mutableStateOf(false) }
        val difficulties = arrayOf("EASY", "NORMAL", "DIFFICULT")
        val arrRounds = arrayOf(5, 10 ,15)
        var arrStatus by rememberSaveable { mutableStateOf(arrayOf(false, false, false)) }
        var sliderValue by remember { mutableStateOf(0f) }
        var finishValue by remember { mutableStateOf("") }
        var timeCount by remember { mutableStateOf(0f) }
        Column (
            modifier = Modifier.constrainAs(difficulty) {
                top.linkTo(parent.top)
                bottom.linkTo(rounds.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                label = { Text("DIFFICULTY") },
                enabled = false,
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier
                    .clickable { expanded = true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                difficulties.forEach { difficulty ->
                    DropdownMenuItem(text = { Text(text = difficulty) }, onClick = {
                        expanded = false
                        selectedText = difficulty
                    })
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(rounds) {
                    top.linkTo(difficulty.bottom)
                    bottom.linkTo(time.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(text = "Rounds")
            Column {
                arrRounds.indices.forEach { index ->
                    RadioButton(
                        selected = arrStatus[index],
                        onClick = { arrStatus[index] = !arrStatus[index] },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.secondary,
                            unselectedColor = MaterialTheme.colorScheme.tertiary
                        )
                    )

                }
            }
        }
        Slider(
            value = timeCount,
            onValueChange = { timeCount = it },
            onValueChangeFinished = { finishValue = sliderValue.toString()},
            valueRange = 1f..10f,
            steps = 9,
            modifier = Modifier
                .width(370.dp)
                .constrainAs(time) {
                    top.linkTo(rounds.bottom)
                    bottom.linkTo(darkMode.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .constrainAs(darkMode) {
                    top.linkTo(time.bottom)
                    bottom.linkTo(returnMenue.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Icona activar mode oscur",
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Switch(checked = VIEW_MODEL.darkThem, onCheckedChange = {VIEW_MODEL.changeDarkThem(!VIEW_MODEL.darkThem)})
        }
        Button(
            onClick = {navigationController.navigate(Routes.MenuScreen.route)}) {
            Text(text = "Return to menu")
        }
    }

}
