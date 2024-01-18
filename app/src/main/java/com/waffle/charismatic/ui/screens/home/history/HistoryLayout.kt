package com.waffle.charismatic.ui.screens.home.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.waffle.charismatic.data.response.HistoryData
import com.waffle.charismatic.ui.components.HistoryItem
import com.waffle.charismatic.ui.components.UniversalTopBar

@Composable
fun HistoryLayout(modifier: Modifier = Modifier, navController: NavController, uiState: UiState) {

    Box(modifier = modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background) ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.onPrimary)
        }
        Column {
            UniversalTopBar(name = "History", navController = navController)
            if(uiState.isSuccess.data != null) {
                LazyColumn {
                    items(uiState.isSuccess.data) { item ->
                        HistoryItem(data = item ?: HistoryData(), navController = navController)
                    }
                }
            }

        }
    }


}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview()
//@Composable
//private fun HistoryScreenPreview() {
//    CharismaticTheme {
//        HistoryLayout()
//    }
//}