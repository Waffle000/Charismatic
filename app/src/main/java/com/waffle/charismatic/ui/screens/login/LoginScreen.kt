package com.waffle.charismatic.ui.screens.login

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.waffle.charismatic.data.request.LoginRequest
import com.waffle.charismatic.data.response.LoginResponse
import com.waffle.charismatic.module.SharedPreference
import com.waffle.charismatic.ui.navigation.Screen
import com.waffle.charismatic.ui.screens.home.storyboard.StoryboardIntent
import findActivity

@Composable
fun LoginScreen(navController: NavController, uiState: UiState, onLoginIntent: (LoginIntent) -> Unit) {
    val context = LocalContext.current
    val sp = SharedPreference(context)
    onLoginIntent(LoginIntent.GoogleLogin(context))
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val data = LoginRequest(accessToken = account.idToken)
                sp.userEmail = account.email ?: ""
                onLoginIntent(LoginIntent.PostLogin(data))
            } catch (e: ApiException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    if (uiState.isSuccess != LoginResponse()) {
        uiState.isSuccess.data.let {
            sp.userName = it?.fullname ?: ""
            sp.userToken = it?.token ?: ""
            sp.userImage = it?.picture ?: ""
            sp.isLogin = true
        }
        navController.navigate(Screen.Home.route){
            popUpTo(Screen.Login.route) { inclusive = true }
        }
    }

    LoginLayout(onSignInButtonClicked = {
        val signInIntent = uiState.isSuccessGoogle?.signInIntent
        googleSignInLauncher.launch(signInIntent)
    })
}