package com.waffle.charismatic.ui.screens.home.edit_image

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.waffle.charismatic.domain.model.Detail
import com.waffle.charismatic.ui.components.EditTextForm
import com.waffle.charismatic.ui.components.UniversalTopBar
import createPartFromString

@Composable
fun EditImageDetailLayout(
    modifier: Modifier = Modifier, detail: Detail, navController: NavController,
    uiState: UiState, onEditImageIntent: (EditImageIntent) -> Unit
) {
    var backgroundDesc by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = detail.id) {
        onEditImageIntent(EditImageIntent.GetEditImageDetail(detail.id))
        backgroundDesc = uiState.isSuccessSingleDetail.data?.prompt ?: ""
    }

    val painter = rememberAsyncImagePainter(uiState.isSuccessSingleDetail.data?.imageUrl)
    val state = painter.state

    val transition by animateFloatAsState(
        targetValue = if (state is AsyncImagePainter.State.Success) 1f else 0f,
        label = ""
    )

    val animation = rememberInfiniteTransition()
    val progress by animation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Restart,
        ), label = ""
    )

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
            Box(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxSize()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .weight(1f)
            ) {

                if (state is AsyncImagePainter.State.Loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .scale(progress)
                            .alpha(1f - progress)
                            .border(
                                5.dp,
                                color = MaterialTheme.colorScheme.onPrimary,
                                shape = CircleShape
                            )
                    )
                }
                Image(
                    painter = painter,
                    contentDescription = "custom transition based on painter state",
                    modifier = Modifier
                        .alpha(transition)
                        .fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(4.dp)
                            )
                            .padding(8.dp)
                            .clickable(onClick = {
                                val downloadManager = context.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
                                val uri = Uri.parse(uiState.isSuccessSingleDetail.data?.imageUrl)
                                val request = DownloadManager.Request(uri)
                                request.setVisibleInDownloadsUi(true)
                                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                request.setDestinationInExternalPublicDir(
                                    Environment.DIRECTORY_DOWNLOADS,
                                    uri.lastPathSegment
                                )
                                downloadManager.enqueue(request)
                            })
                    ) {
                        Icon(
                            Icons.Rounded.Download,
                            contentDescription = "Icon Download",
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }
            EditTextForm(
                title = "Deskripsi Background",
                value = backgroundDesc,
                onValueChange = { backgroundDesc = it })
            Button(
                onClick = {
                    val prompt = createPartFromString(backgroundDesc)
                    onEditImageIntent(EditImageIntent.PostGenerateEditImage(uiState.isSuccessSingleDetail.data?.productImageId ?: 0, prompt))
                          }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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
//private fun EditImageDetailScreenPreview() {
//    CharismaticTheme {
//        EditImageDetailLayout()
//    }
//}