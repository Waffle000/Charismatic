package com.waffle.charismatic.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.waffle.charismatic.ui.theme.titleMedium

@Composable
fun UniversalTopBar(name: String, navController: NavController, onBackButton:() -> Unit = {navController.popBackStack()}) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth().padding(top = 8.dp)
    ) {
        IconButton(modifier = Modifier.align(Alignment.CenterStart), onClick = onBackButton) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back Button", tint = MaterialTheme.colorScheme.onPrimary)
        }
        Text(text = name, style = titleMedium, color = MaterialTheme.colorScheme.onPrimary)
    }
}