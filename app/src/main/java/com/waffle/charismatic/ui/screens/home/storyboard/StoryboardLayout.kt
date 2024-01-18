package com.waffle.charismatic.ui.screens.home.storyboard

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.waffle.charismatic.data.response.CopywrittingResponse
import com.waffle.charismatic.data.response.StoryboardResponse
import com.waffle.charismatic.domain.model.Detail
import com.waffle.charismatic.ui.components.EditTextForm
import com.waffle.charismatic.ui.components.MultilineEditTextForm
import com.waffle.charismatic.ui.components.UniversalTopBar
import com.waffle.charismatic.ui.theme.CharismaticTheme

@Composable
fun StoryboardLayout(
    modifier: Modifier = Modifier,
    navController: NavController,
    uiState: UiState,
    productTitle : String,
    onValueProductTitleChange: (String) -> Unit,
    brandName : String,
    onValueBrandNameChange: (String) -> Unit,
    productType : String,
    onValueProductTypeChange: (String) -> Unit,
    marketTarget : String,
    onValueMarketTargetChange: (String) -> Unit,
    superiority : String,
    onValueSuperiorityChange: (String) -> Unit,
    duration : String,
    onValueDurationChange: (String) -> Unit,
    onGenerateButtonClicked: () -> Unit,
    onSuccess: (Detail) -> Unit,
    onFailed: (String) -> Unit,
    onLoading: @Composable () -> Unit
) {
    if (uiState.isError.isNotBlank()) {
        onFailed(uiState.isError)
    }
    LaunchedEffect(key1 = uiState.isSuccess) {
        if (uiState.isSuccess != StoryboardResponse()) {
            onSuccess(Detail(uiState.isSuccess.data?.id ?: 0, uiState.isSuccess.data?.productTitle ?: ""))
        }
    }

    if (uiState.isLoading) {
        onLoading()
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            UniversalTopBar(name = "Storyboard", navController = navController)
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
                    .weight(1f)
            ) {
                EditTextForm(title = "Video Title", value = productTitle, onValueChange = onValueProductTitleChange)
                EditTextForm(title = "Brand Name", value = brandName, onValueChange = onValueBrandNameChange)
                EditTextForm(title = "Product Type", value = productType, onValueChange = onValueProductTypeChange)
                EditTextForm(title = "Target Market", value = marketTarget, onValueChange = onValueMarketTargetChange)
                MultilineEditTextForm(title = "Product Advantages", value = superiority, onValueChange = onValueSuperiorityChange)
                EditTextForm(title = "Video Duration (Second)", value = duration, onValueChange = onValueDurationChange)
            }

            Button(onClick = onGenerateButtonClicked, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(
                    "Generate Storyboard",
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview()
//@Composable
//private fun StoryBoardScreenPreview() {
//    CharismaticTheme {
//        StoryboardLayout()
//    }
//}