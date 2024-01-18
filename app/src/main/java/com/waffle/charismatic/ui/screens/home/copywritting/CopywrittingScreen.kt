package com.waffle.charismatic.ui.screens.home.copywritting

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.gson.Gson
import com.waffle.charismatic.data.request.StoryboardRequest
import com.waffle.charismatic.domain.model.Detail
import com.waffle.charismatic.ui.components.LoadingGenerate
import com.waffle.charismatic.ui.navigation.Screen
import com.waffle.charismatic.ui.screens.home.storyboard.StoryboardIntent
import createPartFromString
import getFileName
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import uriToFile

@Composable
fun CopywrittingScreen(navController: NavController, uiState: UiState, onCopywrittingIntent: (CopywrittingIntent) -> Unit) {
    var productTitle by remember { mutableStateOf("") }
    var brandName by remember { mutableStateOf("") }
    var productType by remember { mutableStateOf("") }
    var marketTarget by remember { mutableStateOf("") }
    var superiority by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    CopywrittingLayout(navController = navController,
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
        onValueImageChange = {
            imageUri = it
        },
        onGenerateButtonClicked = {
            Log.e("TAG", "CopywrittingScreen: SAMPE SINI", )
            val titlePart = createPartFromString(productTitle)
            val brandNamePart = createPartFromString(brandName)
            val productTypePart = createPartFromString(productType)
            val marketTargetPart = createPartFromString(marketTarget)
            val superiorityPart = createPartFromString(superiority)
            val requestImageFile =
                imageUri?.let { uriToFile(it, context).asRequestBody("image/png".toMediaTypeOrNull()) }
            val imageMultipart = requestImageFile.let { it1 ->
                it1?.let {
                    MultipartBody.Part.createFormData(
                        "product_image",
                        imageUri?.let { getFileName(context, it) }.toString(),
                        it
                    )
                }
            }

            onCopywrittingIntent(CopywrittingIntent.PostCopywritting(titlePart, brandNamePart, productTypePart, marketTargetPart, superiorityPart, imageMultipart))
        },
        onSuccess = {detail->
            val jsonDetail = Uri.encode(Gson().toJson(detail))
            val route = Screen.CopyWrittingDetail.route.replace(Screen.Values.DetailValue, jsonDetail)
            navController.navigate(route)
        },
        onLoading = {
            LoadingGenerate()
        },
        onFailed = {

        })
}