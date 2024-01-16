package com.example.trivialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trivialapp.View.GameScreen
import com.example.trivialapp.View.LunchScreen
import com.example.trivialapp.View.MenuScreen
import com.example.trivialapp.View.Routes
import com.example.trivialapp.View.SettingsScreen
import com.example.trivialapp.model.MyViewModel
import com.example.trivialapp.ui.theme.TrivialAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val VIEW_MODEL by viewModels<MyViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
            TrivialAppTheme (VIEW_MODEL.darkThem) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.SettingsScreen.route
                    ) {
                        composable(Routes.LunchScreen.route) { LunchScreen(navigationController, VIEW_MODEL) }
                        composable(Routes.MenuScreen.route) { MenuScreen(navigationController, VIEW_MODEL) }
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
                                VIEW_MODEL,
                                backStackEntry.arguments?.getString("dificultad")
                            )
                        }
                        /* esta en casa
                        composable(
                            Routes.ResultScreen.route,
                            VIEW_MODEL,
                            arguments = listOf(
                                navArgument(
                                    "dificultad",
                                    { defaultValue = "Fàcil" })
                            )
                        ) { backStackEntry ->
                            Re(
                                navigationController,
                                backStackEntry.arguments?.getString("dificultad")
                            )
                        }

                         */
                        composable(Routes.SettingsScreen.route) { SettingsScreen(navigationController, VIEW_MODEL) }
                    }
                }
            }
        }
    }
}
