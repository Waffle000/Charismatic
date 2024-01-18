package com.waffle.charismatic.ui.screens.home

import androidx.lifecycle.ViewModel
import com.waffle.charismatic.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val useCase: UserUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

//    fun processIntent(intent: LoginIntent) {
//        when (intent) {
//
//        }
//    }

    private fun showLoading() {
        _uiState.value = UiState(isLoading = true)
    }

    private fun hideLoading() {
        _uiState.value = UiState(isLoading = false)
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val isSuccess: String = "",
    val isError: String = ""
)

//sealed interface LoginIntent {
//    data class OnLogin(val email: String, val password: String) : LoginIntent
//}