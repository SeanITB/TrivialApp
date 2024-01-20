package com.example.trivialapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trivialapp.View.HoritzontalGameScreen
import com.example.trivialapp.View.GameScreen
import com.example.trivialapp.View.LunchScreen
import com.example.trivialapp.View.MenuScreen
import com.example.trivialapp.View.ResultScreen
import com.example.trivialapp.navigation.Routes
import com.example.trivialapp.View.SettingsScreen
import com.example.trivialapp.ViewModel.MyViewModel
import com.example.trivialapp.model.RememberGameInfo
import com.example.trivialapp.ui.theme.TrivialAppTheme

class MainActivity : ComponentActivity() {
    val myViewModel by viewModels<MyViewModel>()
    val gameInfo by viewModels<RememberGameInfo>()
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
                    val configuration = LocalConfiguration.current
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.LunchScreen.route
                    ) {
                        composable(Routes.LunchScreen.route) { LunchScreen(navigationController, myViewModel) }
                        composable(Routes.MenuScreen.route,) { MenuScreen(navigationController, myViewModel)}
                        composable(Routes.VerticalGameScreen.route) { GameScreen(navigationController, myViewModel, gameInfo) }
                        composable(Routes.HoritzontalGameScreen.route) { HoritzontalGameScreen(navigationController, myViewModel, gameInfo)}
                        composable(
                            Routes.ResultScreen.route) {
                            ResultScreen(
                                navigationController,
                                myViewModel, gameInfo
                            )
                        }
                        composable(Routes.SettingsScreen.route) { SettingsScreen(navigationController, myViewModel) }
                    }
                }
            }
        }
    }
}

@Composable
fun orientation(): Boolean {
    val configuration = LocalConfiguration.current
    val isVertical: Boolean = when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> true
        else -> false
    }
    return isVertical
}
