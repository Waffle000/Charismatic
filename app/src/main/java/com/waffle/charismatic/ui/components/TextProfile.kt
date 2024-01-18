package com.waffle.charismatic.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.waffle.charismatic.ui.theme.bodyMedium

@Composable
fun TextProfile(value: String, padding : Int = 0) {
    Text(
        text = value,
        style = bodyMedium,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(padding.dp)
    )
}