package com.waffle.charismatic.ui.screens.home.edit_image

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.waffle.charismatic.domain.model.Detail
import com.waffle.charismatic.ui.components.UniversalTopBar
import com.waffle.charismatic.ui.navigation.Screen

@Composable
fun EditImageDetailListLayout(
    modifier: Modifier = Modifier, detail: Detail, navController: NavController,
    uiState: UiState, onEditImageIntent: (EditImageIntent) -> Unit
) {
    LaunchedEffect(key1 = detail.id) {
        onEditImageIntent(EditImageIntent.GetEditImageListDetail(detail.id))
    }
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column {
            UniversalTopBar(name = detail.name ?: "", navController = navController)
            if (uiState.isSuccessMultiple.data?.generatedImages != null) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier
                        .padding(top = 16.dp, end = 8.dp, start = 8.dp)
                        .weight(1f)
                ) {
                    items(uiState.isSuccessMultiple.data.generatedImages) { item ->
                        var showDialog by remember { mutableStateOf(false) }
                        if (showDialog) {
                            Dialog(onDismissRequest = { showDialog = false }) {
                                Column(modifier = Modifier.background(
                                    MaterialTheme.colorScheme.background,
                                    RoundedCornerShape(8.dp)
                                )) {
                                    Icon(
                                        Icons.Rounded.Close,
                                        contentDescription = "close dialog",
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier
                                            .clickable(onClick = { showDialog = false })
                                            .padding(16.dp)
                                            .align(Alignment.End)
                                    )
                                    AsyncImage(
                                        model = item?.imageUrl,
                                        contentDescription = "Selected Image",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 16.dp, end = 16.dp)
                                    )
                                    Button(
                                        onClick = {
                                            val downloadManager = context.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
                                            val uri = Uri.parse(item?.imageUrl ?: "")
                                            val request = DownloadManager.Request(uri)
                                            request.setVisibleInDownloadsUi(true)
                                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                            request.setDestinationInExternalPublicDir(
                                                Environment.DIRECTORY_DOWNLOADS,
                                                uri.lastPathSegment
                                            )
                                            downloadManager.enqueue(request)
                                            Toast.makeText(context, "Download will start", Toast.LENGTH_SHORT).show()
                                            showDialog = false
                                        }, modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
                                    ) {
                                        Text(
                                            "Download Image",
                                            color = MaterialTheme.colorScheme.onSecondary,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }

                                }


                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {
                            AsyncImage(
                                model = item?.imageUrl,
                                contentDescription = "list image",
                                modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(4.dp)
                                    )
                                    .padding(8.dp)
                                    .clickable(onClick = { showDialog = true })
                            )
                        }

                    }
                }
            }
            Button(
                onClick = {
                    val detailItem = Detail(
                        id = uiState.isSuccessMultiple.data?.generatedImages?.last()?.id ?: 0,
                        "Edit Image Detail"
                    )
                    val json = Uri.encode(Gson().toJson(detailItem))
                    val route =
                        Screen.EditImageDetail.route.replace(Screen.Values.DetailValue, json)
                    navController.navigate(route)
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
//private fun StoryBoardScreenPreview() {
//    CharismaticTheme {
//        EditImageDetailListLayout()
//    }
//}