package com.waffle.charismatic.ui.screens.home.storyboard

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.google.gson.Gson
import com.waffle.charismatic.data.request.StoryboardRequest
import com.waffle.charismatic.ui.components.LoadingGenerate
import com.waffle.charismatic.ui.navigation.Screen

@Composable
fun StoryboardScreen(
    navController: NavController,
    uiState: UiState,
    onStoryboardIntent: (StoryboardIntent) -> Unit
) {
    var productTitle by remember { mutableStateOf("") }
    var brandName by remember { mutableStateOf("") }
    var productType by remember { mutableStateOf("") }
    var marketTarget by remember { mutableStateOf("") }
    var superiority by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("0") }

    StoryboardLayout(
        navController = navController,
        uiState = uiState,
        productTitle = productTitle,
        onValueProductTitleChange = {
            productTitle = it
        },
        brandName = brandName,
        onValueBrandNameChange = {
            brandName = it
        },
        productType = productType,
        onValueProductTypeChange = {
            productType = it
        },
        marketTarget = marketTarget,
        onValueMarketTargetChange = {
            marketTarget = it
        },
        superiority = superiority,
        onValueSuperiorityChange = {
            superiority = it
        },
        duration = duration,
        onValueDurationChange = {
            duration = it
        },
        onGenerateButtonClicked = {
            val data = StoryboardRequest(
                productTitle = productTitle,
                brandName = brandName,
                productType = productType,
                marketTarget = marketTarget,
                superiority = superiority,
                duration = duration.toInt()
            )
            onStoryboardIntent(StoryboardIntent.PostStoryboard(data))
        },
        onSuccess = {detail ->
            val json = Uri.encode(Gson().toJson(detail))
            val route = Screen.StoryboardDetail.route.replace(Screen.Values.DetailValue, json)
            navController.navigate(route)
        },
        onLoading = {
            LoadingGenerate()
        },
        onFailed = {

        })
}