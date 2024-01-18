package com.waffle.charismatic.ui.screens.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.waffle.charismatic.domain.usecase.FeatureUseCase
import com.waffle.charismatic.domain.usecase.UserUseCase
import com.waffle.charismatic.ui.screens.login.LoginIntent
import com.waffle.charismatic.ui.screens.login.LoginViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(userUseCase: UserUseCase) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    fun processIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.GoogleLogin -> getGoogleSignInClient(intent.context)
        }
    }

    private val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    .requestIdToken(LoginViewModel.TOKEN)
    .requestEmail()
    .build()

    private fun getGoogleSignInClient(context: Context) {
        _uiState.value  = UiState(
            isSuccessGoogle = GoogleSignIn.getClient(
                context,
                googleSignInOptions
            )
        )
    }
}
data class UiState(
    val isLoading: Boolean = false,
    val isSuccessGoogle : GoogleSignInClient? = null,
    val isError: String = ""
)

sealed interface ProfileIntent {
    data class GoogleLogin(val context: Context) : ProfileIntent
}