package com.juliocezar.gw2achievguide

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.juliocezar.gw2achievguide.common.data.remote.RetrofitClient
import com.juliocezar.gw2achievguide.common.data.remote.model.GW2ApiService
import com.juliocezar.gw2achievguide.ui.theme.GW2AchievGuideTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val retrofit = RetrofitClient.getRetrofitInstance("2F1C9407-1414-FE4F-AD76-9A3340C18B436EE48AF7-7A47-4EA9-AAB0-5251CEA0A800")
        //val retrofit = RetrofitClient.getRetrofitInstance(userApiKey)
        val apiService = retrofit.create(GW2ApiService::class.java)

        setContent {
            GW2AchievGuideTheme {
                val accountName = remember { mutableStateOf("Loading...")}

                lifecycleScope.launch {
                    try {
                        val account = apiService.getAccount()
                        accountName.value = account.name  // Atualiza o estado com o nome
                    } catch (e: Exception) {
                        accountName.value = "Erro: ${e.message}"
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = accountName.value,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
