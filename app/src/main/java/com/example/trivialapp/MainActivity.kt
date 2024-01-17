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
import com.example.trivialapp.View.ResultScreen
import com.example.trivialapp.navigation.Routes
import com.example.trivialapp.View.SettingsScreen
import com.example.trivialapp.ViewModel.MyViewModel
import com.example.trivialapp.ui.theme.TrivialAppTheme

class MainActivity : ComponentActivity() {
    val myViewModel by viewModels<MyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrivialAppTheme(myViewModel.darkThem) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.GameScreen.route
                    ) {
                        composable(Routes.LunchScreen.route) { LunchScreen(navigationController, myViewModel) }
                        composable(Routes.MenuScreen.route,) { MenuScreen(navigationController, myViewModel)}
                        composable(Routes.GameScreen.route) { GameScreen(navigationController, myViewModel) }
                        composable(
                            Routes.ResultScreen.route,
                            arguments = listOf(navArgument("dificultad", { defaultValue = "Fàcil" }))
                        ) { backStackEntry ->
                            ResultScreen(
                                navigationController,
                                myViewModel,
                                backStackEntry.arguments?.getInt("numIntents") ?: 0,
                                backStackEntry.arguments?.getBoolean("enhorabona") ?: true,
                            )
                        }
                        composable(Routes.SettingsScreen.route) { SettingsScreen(navigationController, myViewModel) }
                    }
                }
            }
        }
    }
}
