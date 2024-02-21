package com.waffle.charismatic.ui.screens.home.edit_image

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.waffle.charismatic.R
import com.waffle.charismatic.data.response.EditImageResponse
import com.waffle.charismatic.domain.model.Detail
import com.waffle.charismatic.ui.components.EditTextForm
import com.waffle.charismatic.ui.components.MultilineEditTextForm
import com.waffle.charismatic.ui.components.UniversalTopBar
import com.waffle.charismatic.ui.theme.bodyMedium
import dev.eren.removebg.RemoveBg
import getFileName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import saveBitmapAndGetUri
import timeStamp
import uriToBitmap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditImageLayout(
    modifier: Modifier = Modifier,
    navController: NavController,
    uiState: UiState,
    productTitle: String,
    onValueProductTitleChange: (String) -> Unit,
    backgroundDesc: String,
    onValueBackgroundDescChange: (String) -> Unit,
    onValueImageChange: (Uri) -> Unit,
    onGenerateButtonClicked: () -> Unit,
    onSuccess: (Detail) -> Unit,
    onFailed: (String) -> Unit,
    onLoading: @Composable () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var isLoadingDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                isLoadingDialog = true
                imageUri = it.data?.data
                showDialog = true
                isLoadingDialog = false
            }
        }

    var fileName by remember { mutableStateOf("Empty Image") }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_image))

    if (showDialog) {
        Dialog(onDismissRequest = { }) {
            Column(
                modifier = Modifier.background(
                    MaterialTheme.colorScheme.background,
                    RoundedCornerShape(8.dp)
                )
            ) {
                Icon(
                    Icons.Rounded.Close,
                    contentDescription = "close dialog",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .clickable(onClick = { showDialog = false })
                        .padding(16.dp)
                        .align(Alignment.End)
                )
                if (isLoadingDialog) {
                    LottieAnimation(composition = composition)
                } else {
                    AsyncImage(
                        imageUri,
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    )

                }

                Button(
                    onClick = {
                        fileName = imageUri?.let { getFileName(context, it).toString() }.toString()
                        imageUri?.let { it1 -> onValueImageChange(it1) }
                        showDialog = false
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

    LaunchedEffect(key1 = uiState.isSuccessSingle) {
        if (uiState.isSuccessSingle != EditImageResponse()) {
            onSuccess(Detail(uiState.isSuccessSingle.data?.id ?: 0, "Edit Image Detail"))
        }
    }

    if (uiState.isLoading) {
        onLoading()
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Column(modifier = Modifier.fillMaxHeight()) {
                UniversalTopBar(name = "Edit Background Image", navController = navController)
                EditTextForm(
                    title = "Title Edit Background Image",
                    value = productTitle,
                    onValueChange = onValueProductTitleChange
                )
                MultilineEditTextForm(
                    title = "Background Description",
                    value = backgroundDesc,
                    onValueChange = onValueBackgroundDescChange
                )
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
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    "Generate Background",
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
//private fun EditImageScreenPreview() {
//    CharismaticTheme {
//        EditImageLayout()
//    }
//}