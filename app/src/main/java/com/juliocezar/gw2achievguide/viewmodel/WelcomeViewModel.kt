package com.juliocezar.gw2achievguide.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliocezar.gw2achievguide.common.data.remote.GW2ApiService
import com.juliocezar.gw2achievguide.common.data.remote.RetrofitClient
import com.juliocezar.gw2achievguide.common.data.remote.RetrofitFactory
import com.juliocezar.gw2achievguide.common.data.remote.model.AccountDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val retrofitFactory: RetrofitFactory): ViewModel()  {

    init {
        Log.d("WelcomeViewModel", "ViewModel criado: ${this.hashCode()}")
    }

    private val _isLoading = MutableStateFlow(false)
            val isLoading: StateFlow<Boolean> = _isLoading

    private val _apiKey = MutableStateFlow("")
            val apiKey: StateFlow<String> = _apiKey

    private val _accountResult = MutableStateFlow<AccountResult>(AccountResult.Idle)
            val accountResult: StateFlow<AccountResult> = _accountResult

    sealed class AccountResult{
        data object Idle : AccountResult()
        data class Success(val account: AccountDTO) : AccountResult()
        data class Error(val message: String) : AccountResult()
    }

    private fun getApiService(): GW2ApiService {
        val retrofit = retrofitFactory.createRetrofit(_apiKey.value)
        return retrofit.create(GW2ApiService::class.java)
    }

    fun validateApiKey() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val account = getApiService().getAccount()
                Log.d("WelcomeViewModel", "Validado com sucesso: ${account.name}")
                _accountResult.value = AccountResult.Success(account)
            } catch (e: Exception) {
                Log.d("WelcomeViewModel", "Erro na validação: ${e.message}")
                _accountResult.value = AccountResult.Error(e.message ?: "Erro desconhecido")
            }

            finally {
                _isLoading.value = false

            }
        }
    }

    fun updateApiKey(newKey: String) {
            _apiKey.value = newKey
    }
}