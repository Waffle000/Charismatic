package com.waffle.charismatic.ui.screens.home.copywritting

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.waffle.charismatic.data.response.CopywrittingResponse
import com.waffle.charismatic.data.response.StoryboardResponse
import com.waffle.charismatic.domain.model.Detail
import com.waffle.charismatic.ui.components.EditTextForm
import com.waffle.charismatic.ui.components.MultilineEditTextForm
import com.waffle.charismatic.ui.components.UniversalTopBar
import com.waffle.charismatic.ui.screens.home.storyboard.StoryboardLayout
import com.waffle.charismatic.ui.theme.CharismaticTheme
import com.waffle.charismatic.ui.theme.bodyMedium
import getFileName

@Composable
fun CopywrittingLayout(
    modifier: Modifier = Modifier,
    navController: NavController,
    uiState: UiState,
    productTitle: String,
    onValueProductTitleChange: (String) -> Unit,
    brandName: String,
    onValueBrandNameChange: (String) -> Unit,
    productType: String,
    onValueProductTypeChange: (String) -> Unit,
    marketTarget: String,
    onValueMarketTargetChange: (String) -> Unit,
    superiority: String,
    onValueSuperiorityChange: (String) -> Unit,
    onValueImageChange: (Uri) -> Unit,
    onGenerateButtonClicked: () -> Unit,
    onSuccess: (Detail) -> Unit,
    onFailed: (String) -> Unit,
    onLoading: @Composable () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                imageUri = it.data?.data
                showDialog = true
            }
        }

    val context = LocalContext.current

    var fileName by remember { mutableStateOf("Empty Image") }

    if (showDialog) {
        Dialog(onDismissRequest = { }) {
            Column {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                )

                Button(
                    onClick = {
                        showDialog = false
                        fileName = imageUri?.let { getFileName(context, it) }.toString()
                        imageUri?.let { onValueImageChange(it) }
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
                ) {
                    Text(
                        "Choose Image",
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

        }
    }

    if (uiState.isError.isNotBlank()) {
        onFailed(uiState.isError)
    }

    LaunchedEffect(key1 = uiState.isSuccess) {
        if (uiState.isSuccess != CopywrittingResponse()) {
            onSuccess(Detail(uiState.isSuccess.data?.id ?: 0, uiState.isSuccess.data?.title ?: ""))
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
            UniversalTopBar(name = "Copywritting", navController = navController)
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
                    .weight(1f)
            ) {
                EditTextForm(title = "Copywriting Title", value = productTitle, onValueChange = onValueProductTitleChange)
                EditTextForm(title = "Brand Name", value = brandName, onValueChange = onValueBrandNameChange)
                EditTextForm(title = "Product Type", value = productType, onValueChange = onValueProductTypeChange)
                EditTextForm(title = "Target Market", value = marketTarget, onValueChange = onValueMarketTargetChange)
                MultilineEditTextForm(title = "Product Advantages", value = superiority, onValueChange = onValueSuperiorityChange)
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onPrimary,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Upload Image",
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = bodyMedium,
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(8.dp)
                                .clickable(onClick = {
                                    val intent =
                                        Intent(Intent.ACTION_OPEN_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE)
                                    intent.type = "image/*"
                                    launcher.launch(intent)
                                })
                        )

                        Text(
                            text = fileName,
                            color = MaterialTheme.colorScheme.secondary,
                            style = bodyMedium,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            Button(
                onClick = onGenerateButtonClicked, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    "Generate Text",
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
//private fun CopywrittingScreenPreview() {
//    CharismaticTheme {
//        CopywrittingLayout()
//    }
//}