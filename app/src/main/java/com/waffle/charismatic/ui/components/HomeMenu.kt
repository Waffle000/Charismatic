package com.waffle.charismatic.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.waffle.charismatic.ui.theme.titleSmall

@Composable
fun HomeMenu(modifier: Modifier = Modifier, value : String, image : Int) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
            .fillMaxHeight(),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "icon_menu",
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp))
            Text(
                text = value,
                color = MaterialTheme.colorScheme.onSecondary,
                style = titleSmall,
                textAlign = TextAlign.Center)
        }
    }
}