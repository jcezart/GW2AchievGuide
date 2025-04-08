package com.juliocezar.gw2achievguide.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliocezar.gw2achievguide.common.data.remote.GW2ApiService
import com.juliocezar.gw2achievguide.common.data.remote.model.AccountDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val apiService: GW2ApiService): ViewModel()  {

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

    fun validateApiKey() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val account = apiService.getAccount()
                _accountResult.value = AccountResult.Success(account)
            } catch (e: Exception) {
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