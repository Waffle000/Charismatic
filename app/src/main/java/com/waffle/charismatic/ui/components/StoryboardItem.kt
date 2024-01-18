package com.waffle.charismatic.ui.components

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.PauseCircleOutline
import androidx.compose.material.icons.rounded.PlayCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.waffle.charismatic.data.response.ScenesItem
import com.waffle.charismatic.ui.theme.bodyLarge
import com.waffle.charismatic.ui.theme.bodyMedium

@Composable
fun StoryboardItem(data: ScenesItem) {
    val mediaPlayer = MediaPlayer()
    var isPlay by remember {
        mutableStateOf(false)
    }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Column {
                Icon(
                    Icons.Rounded.Close,
                    contentDescription = "close dialog",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .clickable(onClick = { showDialog = false })
                        .padding(16.dp)
                        .align(Alignment.End)
                )
                AsyncImage(
                    model = data.illustrationUrl,
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                )
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            text = data.title ?: "",
            color = MaterialTheme.colorScheme.secondary,
            style = bodyLarge,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )

        Row {
            AsyncImage(
                model = data.illustrationUrl,
                contentScale = ContentScale.Fit,
                contentDescription = "Storyboard",
                modifier = Modifier
                    .size(80.dp)
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .clickable(onClick = {
                        showDialog = true
                    })
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = data.illustration ?: "",
                    color = MaterialTheme.colorScheme.secondary,
                    style = bodyMedium,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
                )

                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primary,
                            CircleShape
                        )
                        .padding(8.dp)
                        .align(Alignment.End)
                ) {
                    Icon(
                        imageVector = if (!isPlay) Icons.Rounded.PlayCircleOutline else Icons.Rounded.PauseCircleOutline,
                        contentDescription = "Play Audio",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.clickable(onClick = {
                            if (!isPlay) {
                                var audioUrl = data.voiceUrl
                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                                try {
                                    isPlay = !isPlay
                                    mediaPlayer.setDataSource(audioUrl)
                                    mediaPlayer.prepare()
                                    mediaPlayer.start()

                                    // Set the completion listener
                                    mediaPlayer.setOnCompletionListener {
                                        mediaPlayer.stop()
                                        mediaPlayer.reset()
                                        mediaPlayer.release()
                                        isPlay = !isPlay
                                    }

                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            } else {
                                if (mediaPlayer.isPlaying) {
                                    mediaPlayer.stop()
                                    mediaPlayer.reset()
                                    mediaPlayer.release()
                                }
                                isPlay = !isPlay
                            }
                        })
                    )

                }
            }
        }
    }
}