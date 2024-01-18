package com.waffle.charismatic.ui.screens.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.waffle.charismatic.R
import com.waffle.charismatic.ui.theme.CharismaticTheme
import com.waffle.charismatic.ui.theme.Poppins
import com.waffle.charismatic.ui.theme.bodyLarge
import com.waffle.charismatic.ui.theme.bodyMedium
import com.waffle.charismatic.ui.theme.headlineLarge

@Composable
fun LoginLayout(onSignInButtonClicked: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)) {
            Image(
                painter = painterResource(id = R.drawable.ll_login),
                contentDescription = "undraw_secure_login_pdn4 1",
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter).padding(16.dp))
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)) {

            Column( modifier = Modifier
                .align(alignment = Alignment.TopStart)) {
                Text(
                    text = "Login",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = headlineLarge
                )
                Text(
                    text = "Login your google account for get better experience using this application",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = bodyLarge,
                    modifier = Modifier.padding(top = 8.dp))
            }

            Box(modifier = Modifier.fillMaxWidth().border( width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(8.dp)
            ).align(Alignment.BottomCenter).clickable(onClick = onSignInButtonClicked)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "google",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart).padding(16.dp))

                Text(
                    text = "Sign in with Google",
                    color = MaterialTheme.colorScheme.secondary,
                    style = bodyMedium,
                    modifier = Modifier.align(Alignment.Center))
            }

        }
    }
}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview()
//@Composable
//private fun LoginScreenPreview() {
//    CharismaticTheme {
//        LoginLayout()
//
//    }
//}