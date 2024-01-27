package com.example.trivialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trivialapp.View.GameScreen
import com.example.trivialapp.View.LunchScreen
import com.example.trivialapp.View.MenuScreen
import com.example.trivialapp.View.ResultScreen
import com.example.trivialapp.navigation.Routes
import com.example.trivialapp.View.SettingsScreen
import com.example.trivialapp.ViewModel.SettingsViewModel
import com.example.trivialapp.ViewModel.GameViewModel
import com.example.trivialapp.ui.theme.TrivialAppTheme

class MainActivity : ComponentActivity() {
    val settingsVM by viewModels<SettingsViewModel>()
    val gameVM by viewModels<GameViewModel>()
    val time: Int = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrivialAppTheme(settingsVM.darkThem) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.SettingsScreen.route,
                        enterTransition = {
                            fadeIn(animationSpec = tween(time)) + slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Left, tween(time)
                            )
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(time)) + slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Down, tween(time)
                            )
                        },
                        popEnterTransition = {
                            fadeIn(animationSpec = tween(time)) + slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up, tween(time)
                            )
                        },
                        popExitTransition = {
                            fadeOut(animationSpec = tween(time)) + slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Right, tween(time)
                            )
                        }
                    ) {
                        composable(Routes.LunchScreen.route) { LunchScreen(navigationController, settingsVM) }
                        composable(Routes.MenuScreen.route,) { MenuScreen(navigationController, settingsVM, gameVM)}
                        composable(Routes.GameScreen.route) { GameScreen(navigationController, settingsVM, gameVM) }
                        composable(Routes.ResultScreen.route) { ResultScreen(navigationController, settingsVM, gameVM) }
                        composable(Routes.SettingsScreen.route) { SettingsScreen(navigationController, settingsVM) }
                    }
                }
            }
        }
    }
}