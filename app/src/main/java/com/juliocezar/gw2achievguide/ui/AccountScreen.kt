package com.juliocezar.gw2achievguide.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.juliocezar.gw2achievguide.viewmodel.WelcomeViewModel

@Composable
fun AccountScreen(navController: NavController, viewModel: WelcomeViewModel){
    val accountData by viewModel.accountResult.collectAsState()
    Log.d("AccountScreen", "Estado atual: $accountData")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        if (accountData is WelcomeViewModel.AccountResult.Success) {
            Text("Welcome, ${(accountData as WelcomeViewModel.AccountResult.Success).account.name}",
                style = MaterialTheme.typography.bodyLarge, fontFamily = textFontBlack, color = Color(0xFFDA291C)
            )
        } else Text("Loading..")
}
}
