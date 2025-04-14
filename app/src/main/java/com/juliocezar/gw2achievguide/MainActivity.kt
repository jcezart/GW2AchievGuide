package com.juliocezar.gw2achievguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.juliocezar.gw2achievguide.ui.AccountScreen
import com.juliocezar.gw2achievguide.ui.WelcomeScreen
import com.juliocezar.gw2achievguide.ui.theme.GW2AchievGuideTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GW2AchievGuideTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "welcome"){
                    composable("welcome") { WelcomeScreen(modifier = Modifier.fillMaxSize(), navController = navController, viewModel = hiltViewModel())
                }
                    composable("account") { AccountScreen(navController = navController, viewModel = hiltViewModel()) }
                }
            }
        }
    }}




