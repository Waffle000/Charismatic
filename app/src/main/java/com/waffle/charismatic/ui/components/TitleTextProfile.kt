package com.waffle.charismatic.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.waffle.charismatic.ui.theme.titleSmall

@Composable
fun TitleTextProfile(value : String) {
    Text(
        text = value,
        style = titleSmall,
        color = MaterialTheme.colorScheme.onPrimary
    )
}