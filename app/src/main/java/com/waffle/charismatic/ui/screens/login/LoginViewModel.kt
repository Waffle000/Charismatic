package com.waffle.charismatic.ui.screens.login

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.waffle.charismatic.R
import com.waffle.charismatic.data.request.LoginRequest
import com.waffle.charismatic.data.response.LoginResponse
import com.waffle.charismatic.domain.usecase.UserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LoginViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.PostLogin -> postLogin(intent.LoginRequest)
            is LoginIntent.GoogleLogin -> getGoogleSignInClient(intent.context)
        }
    }

    private val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(TOKEN)
        .requestEmail()
        .build()

    private fun getGoogleSignInClient(context: Context) {
        _uiState.value  = UiState(isSuccessGoogle = GoogleSignIn.getClient(context, googleSignInOptions))
    }

    private fun postLogin(LoginRequest: LoginRequest) {
        CoroutineScope(Dispatchers.Main).launch {
            userUseCase.postLogin(LoginRequest)
                .onStart {
                    showLoading()
                }
                .catch { err ->
                    hideLoading()
                    _uiState.value = UiState(
                        isError = err.message ?: "Something Error"
                    )
                }
                .collect { result ->
                    hideLoading()
                    result.fold(onSuccess = { data ->
                        _uiState.value = UiState(isSuccess = data)
                    }, onFailure = {
                        _uiState.value = UiState(
                                isError = "Something Error"
                            )

                    })

                }
        }
    }
    
    private fun showLoading() {
        _uiState.value = UiState(isLoading = true)
    }

    private fun hideLoading() {
        _uiState.value = UiState(isLoading = false)
    }

    companion object {
        const val TOKEN = "486443363784-uc5rftc2prusqmgk1jdn0kl399ua1a4i.apps.googleusercontent.com"
    }
}
data class UiState(
    val isLoading: Boolean = false,
    val isSuccess: LoginResponse = LoginResponse(),
    val isSuccessGoogle : GoogleSignInClient? = null,
    val isError: String = ""
)

sealed interface LoginIntent {
    data class PostLogin(val LoginRequest: LoginRequest) : LoginIntent
    data class GoogleLogin(val context: Context) : LoginIntent
}