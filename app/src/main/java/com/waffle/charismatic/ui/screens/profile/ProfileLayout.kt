package com.waffle.charismatic.ui.screens.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.waffle.charismatic.R
import com.waffle.charismatic.module.SharedPreference
import com.waffle.charismatic.ui.components.TextProfile
import com.waffle.charismatic.ui.components.TitleTextProfile
import com.waffle.charismatic.ui.components.UniversalTopBar
import com.waffle.charismatic.ui.theme.CharismaticTheme
import com.waffle.charismatic.ui.theme.red
import com.waffle.charismatic.ui.theme.titleMedium
import com.waffle.charismatic.ui.theme.titleSmall

@Composable
fun ProfileLayout(modifier: Modifier = Modifier, navController: NavController, uiState: UiState, onLogoutButtonClicked: () -> Unit) {
    val context = LocalContext.current
    val sp = SharedPreference(context)
    Box(modifier = modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            UniversalTopBar(name = "Profile", navController = navController)
            Spacer(modifier = Modifier.size(32.dp))

            Column(modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    model = sp.userImage,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = sp.userName,
                    style = titleMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.size(32.dp))

                TitleTextProfile(value = "Name")

                Spacer(modifier = Modifier.size(16.dp))

                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .fillMaxWidth()
                ) {
                    TextProfile(value = sp.userName, 8)
                }

                Spacer(modifier = Modifier.size(32.dp))

                TitleTextProfile(value = "Email")

                Spacer(modifier = Modifier.size(16.dp))

                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .fillMaxWidth()
                ) {
                    TextProfile(value = sp.userEmail, 8)
                }
            }
        }

        Button(onClick = onLogoutButtonClicked, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(red)
        ) {
            Text("Logout", color = MaterialTheme.colorScheme.onSecondary, modifier = Modifier.padding(8.dp))
        }
    }
}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview()
//@Composable
//private fun StoryBoardScreenPreview() {
//    CharismaticTheme {
//        ProfileLayout()
//    }
//}