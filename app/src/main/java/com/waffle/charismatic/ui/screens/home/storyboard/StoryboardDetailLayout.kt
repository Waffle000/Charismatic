package com.waffle.charismatic.ui.screens.home.storyboard

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.waffle.charismatic.domain.model.Detail
import com.waffle.charismatic.ui.components.EditTextForm
import com.waffle.charismatic.ui.components.StoryboardItem
import com.waffle.charismatic.ui.components.UniversalTopBar
import com.waffle.charismatic.ui.navigation.Screen
import com.waffle.charismatic.ui.screens.home.copywritting.CopywrittingIntent
import com.waffle.charismatic.ui.theme.CharismaticTheme
import com.waffle.charismatic.ui.theme.bodyMedium
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun StoryboardDetailLayout(
    modifier: Modifier = Modifier, detail: Detail, navController: NavController,
    uiState: UiState,
    onStoryboardIntent: (StoryboardIntent) -> Unit
) {
    var networkColor by remember { mutableStateOf(Color.Red) }
    
    val context = LocalContext.current

    LaunchedEffect(key1 = detail.id, key2 = true) {
        onStoryboardIntent(StoryboardIntent.GetStoryboardDetail(detail.id))
    }

    LaunchedEffect(key1 = true) {
        if (checkNetwork(context) == NetworkState.AVAILABLE) {
            networkColor= Color.Green
        } else {
            networkColor = Color.Red
        }
        delay(2000)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.onPrimary)
        }
        Column {
            UniversalTopBar(name = detail.name ?: "", navController = navController)
//            Row {
//                Text(text = "Internet Connection", style = bodyMedium, color = MaterialTheme.colorScheme.secondary)
//                Box(modifier = Modifier.size(24.dp).background(networkColor))
//            }
            if (uiState.isSuccessDetail.data?.scenes != null) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(uiState.isSuccessDetail.data.scenes) { item ->
                        if (item != null) {
                            StoryboardItem(item)
                        }
                    }
                }
            }
            Button(
                onClick = {
                    Toast.makeText(context, "Features not yet available", Toast.LENGTH_SHORT).show()
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    "Download ZIP",
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

private fun checkNetwork(context: Context) : NetworkState {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    return if (activeNetwork != null && activeNetwork.isConnected) {
        NetworkState.AVAILABLE
    } else {
        NetworkState.UNAVAILABLE
    }
}

enum class NetworkState {
    AVAILABLE, UNAVAILABLE
}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview()
//@Composable
//private fun StoryBoardScreenPreview() {
//    CharismaticTheme {
//        StoryboardDetailLayout()
//    }
//}