package com.waffle.charismatic.ui.screens.home.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController

@Composable
fun HistoryScreen(navController: NavController, uiState: UiState, onHistoryIntent: (HistoryIntent) -> Unit) {
    LaunchedEffect(key1 = true) {
        onHistoryIntent(HistoryIntent.GetHistory)
    }

    HistoryLayout(navController = navController, uiState = uiState)
}