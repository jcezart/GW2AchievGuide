package com.juliocezar.gw2achievguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
val titleFont = FontFamily(Font(R.font.gw2font))
val textFont = FontFamily(Font(R.font.lato_regular))
val textFontIt = FontFamily(Font(R.font.lato_italic))
val textFontBlack = FontFamily(Font(R.font.lato_black))


// 2F1C9407-1414-FE4F-AD76-9A3340C18B436EE48AF7-7A47-4EA9-AAB0-5251CEA0A800

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, viewModel: WelcomeViewModel = viewModel()){
    val apiKey by viewModel.apiKey.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val accountResult by viewModel.accountResult.collectAsState()
    val uriHandler = LocalUriHandler.current
    val explText = buildAnnotatedString {
        append("To log in into your account, you will need an API KEY, if you don't have one, you can create it at the official website  ( ")
        val start = length
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append("here")
        }
        val end = length
        addStringAnnotation(
            tag = "URL",
            annotation = "https://www.guildwars2.com",
            start = start,
            end = end
        )
        append(" ). If you already have one, please type on the box bellow.")
    }


    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally)  {
        Text(
            style = MaterialTheme.typography.headlineMedium, fontFamily = titleFont, color = Color(0xFFDA291C),
            modifier = Modifier.padding(36.dp),
            text = "Welcome to your Guild Wars 2 Achievement Guide!"
        )
        ClickableText(
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = textFont, color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(18.dp),
            text = explText,
            onClick = {offset -> explText.getStringAnnotations(tag = "URL", start = offset, end = offset).firstOrNull()?.let { annotation -> uriHandler.openUri(annotation.item)}}
        )
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = apiKey,
                onValueChange = { viewModel.updateApiKey(it)},
                label = {Text("Type your API Key")},
                textStyle = TextStyle(fontFamily = textFontIt),
                shape = CircleShape,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.size(8.dp))

            Button(onClick = { viewModel.validateApiKey()},
                enabled = !isLoading && apiKey.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDA291C))
            ) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = textFontBlack,
                    color = Color.White,//MaterialTheme.colorScheme.onBackground,
                    text = "Validate")
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


