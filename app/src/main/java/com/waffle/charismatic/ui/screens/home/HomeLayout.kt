package com.waffle.charismatic.ui.screens.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.waffle.charismatic.R
import com.waffle.charismatic.module.SharedPreference
import com.waffle.charismatic.ui.components.HomeMenu
import com.waffle.charismatic.ui.theme.CharismaticTheme
import com.waffle.charismatic.ui.theme.bodyMedium
import com.waffle.charismatic.ui.theme.headlineMedium
import com.waffle.charismatic.ui.theme.titleSmall

@Composable
fun HomeLayout(
    onStoryboardButtonClicked: () -> Unit,
    onCopywrittingButtonClicked: () -> Unit,
    onEditImageButtonClicked: () -> Unit,
    onHistoryButtonClicked: () -> Unit,
    onProfileButtonClicked: () -> Unit,
    uiState: UiState,
    modifier: Modifier = Modifier,
    sharedPreference: SharedPreference
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.align(Alignment.CenterStart)) {
                    Text(
                        text = "Hi, ${sharedPreference.userName}",
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = headlineMedium
                    )
                    Text(
                        text = "Welcome great entrepreneurs",
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = bodyMedium
                    )
                }
                AsyncImage(
                    model = sharedPreference.userImage,
                    contentScale = ContentScale.Crop,
                    contentDescription = "profile image",
                    modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(54.dp)
                        .align(Alignment.CenterEnd)
                        .clickable(onClick = onProfileButtonClicked)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Free on Beta Version",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = titleSmall,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterStart)
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Icon(
                            Icons.Filled.History,
                            modifier = Modifier.clickable( onClick = {
                                Toast.makeText(context, "Features not yet available", Toast.LENGTH_SHORT).show()
                            }),
                            contentDescription = "History",
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }

                    Spacer(modifier = Modifier.size(8.dp))

                    Box(
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            modifier = Modifier.clickable( onClick = {
                                Toast.makeText(context, "Features not yet available", Toast.LENGTH_SHORT).show()
                            }),
                            contentDescription = "Add",
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(160.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier
                .weight(1f)
                .clickable(onClick = onStoryboardButtonClicked)) {
                HomeMenu(value = "Storyboard Video", image = R.drawable.ill_menu_storyboard)
            }
            Spacer(modifier = Modifier.size(16.dp))
            Box(modifier = Modifier.weight(1f).clickable(onClick = onCopywrittingButtonClicked)) {
                HomeMenu(value = "CopyWriter", image = R.drawable.ill_menu_copywritter)
            }
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(160.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.weight(1f).clickable(onClick = onEditImageButtonClicked)) {
                HomeMenu(
                    value = "Edit Background Image",
                    image = R.drawable.ill_menu_edit_background
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Box(modifier = Modifier.weight(1f).clickable(onClick = onHistoryButtonClicked)) {
                HomeMenu(value = "History", image = R.drawable.ill_menu_history)
            }
        }
    }
}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview()
//@Composable
//private fun HomeScreenPreview() {
//    CharismaticTheme {
//        HomeLayout()
//
//    }
//}