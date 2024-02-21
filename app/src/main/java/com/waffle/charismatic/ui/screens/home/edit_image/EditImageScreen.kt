package com.waffle.charismatic.ui.screens.home.edit_image

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.gson.Gson
import com.waffle.charismatic.data.request.StoryboardRequest
import com.waffle.charismatic.ui.components.LoadingGenerate
import com.waffle.charismatic.ui.navigation.Screen
import com.waffle.charismatic.ui.screens.home.copywritting.CopywrittingIntent
import com.waffle.charismatic.ui.screens.home.storyboard.StoryboardIntent
import createPartFromString
import getFileName
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import uriToFile

@Composable
fun EditImageScreen(navController: NavController, uiState: UiState, onEditImageIntent: (EditImageIntent) -> Unit) {
    var productTitle by remember { mutableStateOf("") }
    var backgroundDesc by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    EditImageLayout(
        navController = navController, uiState = uiState,
        productTitle = productTitle,
        onValueProductTitleChange = {
            productTitle = it
        },
        backgroundDesc = backgroundDesc,
        onValueBackgroundDescChange = {
            backgroundDesc = it
        },
        onValueImageChange = {
            imageUri = it
        },
        onGenerateButtonClicked = {
            val prompt = createPartFromString(backgroundDesc)
            val title = createPartFromString(productTitle)
            val requestImageFile =
                imageUri?.let { uriToFile(it, context).asRequestBody("image/png".toMediaTypeOrNull()) }
            val imageMultipart = requestImageFile.let { it1 ->
                it1?.let {
                    MultipartBody.Part.createFormData(
                        "image",
                        imageUri?.let { getFileName(context, it) }.toString(),
                        it
                    )
                }
            }
            onEditImageIntent(EditImageIntent.PostCreateEditImage(prompt, title, imageMultipart))
        },
        onSuccess = {detail ->
            val json = Uri.encode(Gson().toJson(detail))
            val route = Screen.EditImageDetail.route.replace(Screen.Values.DetailValue, json)
            navController.navigate(route)
        },
        onLoading = {
            LoadingGenerate()
        },
        onFailed = {

        }
    )
}