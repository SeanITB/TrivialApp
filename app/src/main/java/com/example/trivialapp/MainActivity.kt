package com.example.trivialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trivialapp.ui.theme.TrivialAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrivialAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.ResultScreen.route
                    ) {
                        composable(Routes.LunchScreen.route) { LunchScreen(navigationController) }
                        composable(Routes.MenuScreen.route) { MenuScreen(navigationController) }
                        composable(
                            Routes.GameScreen.route,
                            arguments = listOf(
                                navArgument(
                                    "dificultad",
                                    { defaultValue = "Fàcil" })
                            )
                        ) { backStackEntry ->
                            GameScreen(
                                navigationController,
                                backStackEntry.arguments?.getString("dificultad")
                            )
                        }
                        composable(
                            Routes.ResultScreen.route,
                            arguments = listOf(
                                navArgument(
                                    "dificultad",
                                    { defaultValue = "Fàcil" })
                            )
                        ) { backStackEntry ->
                            ResultScreen(
                                navigationController,
                                backStackEntry.arguments?.getString("dificultad"),
                                backStackEntry.arguments?.getInt("numIntents") ?: 0,
                                backStackEntry.arguments?.getBoolean("enhorabona") ?: true,
                            )
                        }
                    }
                }
            }
        }
    }
}
