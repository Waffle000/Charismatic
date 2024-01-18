package com.waffle.charismatic.ui.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waffle.charismatic.R
import com.waffle.charismatic.ui.screens.login.LoginLayout
import com.waffle.charismatic.ui.theme.CharismaticTheme
import com.waffle.charismatic.ui.theme.headlineLarge

@Composable
fun SplashLayout(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "google",
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp))
            Text(
                text = "Charismatic",
                color = MaterialTheme.colorScheme.onPrimary,
                style = headlineLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview()
@Composable
private fun SplashScreenPreview() {
    CharismaticTheme {
        SplashLayout()

    }
}