package com.waffle.charismatic.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.waffle.charismatic.module.SharedPreference
import com.waffle.charismatic.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController, uiState: UiState) {
    val sharedPreference = SharedPreference(LocalContext.current)
    HomeLayout(
        onStoryboardButtonClicked = { navController.navigate(Screen.Storyboard.route) },
        onCopywrittingButtonClicked = { navController.navigate(Screen.CopyWritting.route) },
        onEditImageButtonClicked = { navController.navigate(Screen.EditImage.route) },
        onHistoryButtonClicked = { navController.navigate(Screen.History.route) },
        onProfileButtonClicked = { navController.navigate(Screen.Profile.route)},
        uiState = uiState, sharedPreference = sharedPreference)
}