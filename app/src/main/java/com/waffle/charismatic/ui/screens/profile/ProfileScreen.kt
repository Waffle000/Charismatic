package com.waffle.charismatic.ui.screens.profile

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.android.gms.tasks.OnCompleteListener
import com.waffle.charismatic.module.SharedPreference
import com.waffle.charismatic.ui.navigation.Screen
import com.waffle.charismatic.ui.screens.login.LoginIntent

@Composable
fun ProfileScreen(navController: NavController, uiState: UiState, onProfileIntent: (ProfileIntent) -> Unit) {
    val context = LocalContext.current
    onProfileIntent(ProfileIntent.GoogleLogin(context))
    ProfileLayout(navController = navController, uiState = uiState, onLogoutButtonClicked = {
        uiState.isSuccessGoogle?.signOut()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    SharedPreference(context).resetSharedPref()
                   navController.navigate(Screen.Login.route) {
                       popUpTo(Screen.Profile.route) {
                           inclusive = true
                       }
                       popUpTo(Screen.Home.route) {
                           inclusive = true
                       }
                   }
                } else {
                    Toast.makeText(context, "Failed Logout", Toast.LENGTH_SHORT).show()
                }
            }
    })
}