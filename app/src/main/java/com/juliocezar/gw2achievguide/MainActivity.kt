package com.juliocezar.gw2achievguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.juliocezar.gw2achievguide.ui.AccountScreen
import com.juliocezar.gw2achievguide.ui.WelcomeScreen
import com.juliocezar.gw2achievguide.ui.theme.GW2AchievGuideTheme
import com.juliocezar.gw2achievguide.viewmodel.WelcomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GW2AchievGuideTheme {
                val navController = rememberNavController()
                val viewModel: WelcomeViewModel = hiltViewModel()
                NavHost(navController = navController, startDestination = "welcome", modifier = Modifier.background(
                    MaterialTheme.colorScheme.background)){

                    composable("welcome") { WelcomeScreen(navController = navController,viewModel = viewModel)}
                    composable("account") { AccountScreen(navController = navController, viewModel = viewModel)}

                }
            }
        }
    }}




