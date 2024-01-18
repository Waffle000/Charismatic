package com.waffle.charismatic.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.waffle.charismatic.R
import com.waffle.charismatic.ui.theme.CharismaticTheme
import com.waffle.charismatic.ui.theme.bodyLarge
import com.waffle.charismatic.ui.theme.bodySmall

@Composable
fun LoadingGenerate(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_generate))
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, modifier = Modifier.size(128.dp))
            Spacer(modifier = Modifier.size(32.dp))
            Text(text = "Generating...", color = MaterialTheme.colorScheme.onPrimary, style = bodyLarge)
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "Please do not close Charismatic or lock your screen.", color = MaterialTheme.colorScheme.onPrimary, style = bodySmall)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview()
@Composable
private fun LoadingGenerateScreenPreview() {
    CharismaticTheme {
        LoadingGenerate()
    }
}