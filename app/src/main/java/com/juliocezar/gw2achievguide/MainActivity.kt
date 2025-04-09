package com.juliocezar.gw2achievguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WelcomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// 2F1C9407-1414-FE4F-AD76-9A3340C18B436EE48AF7-7A47-4EA9-AAB0-5251CEA0A800

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, viewModel: WelcomeViewModel = viewModel()){
    val apiKey by viewModel.apiKey.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val accountResult by viewModel.accountResult.collectAsState()

    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally)  {
        Text(
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(36.dp),
            text = "Welcome to your Guild Wars 2 Achievement Guide!"
        )
        Text(
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(18.dp),
            text = "To log in into your account, you will need an API KEY, if you don't have one, you can create it at the official website (link aqui)"
        )
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = apiKey,
                onValueChange = { viewModel.updateApiKey(it)},
                label = {Text("Type your API Key")}
            )
            Button(onClick = { viewModel.validateApiKey()}, enabled = !isLoading && apiKey.isNotEmpty()) {
                Text("Validate")
            }
            if (isLoading) {
                CircularProgressIndicator()
                Text("Validating your API Key...")
            } else {
                when (accountResult) {
                    is WelcomeViewModel.AccountResult.Idle ->
                        Text("")
                    is WelcomeViewModel.AccountResult.Success ->
                        Text("Welcome, ${((accountResult as WelcomeViewModel.AccountResult.Success).account.name)}!")
                    is WelcomeViewModel.AccountResult.Error ->
                        Text("Error: ${(accountResult as WelcomeViewModel.AccountResult.Error).message}")
                }
            }
        }

    }
}


