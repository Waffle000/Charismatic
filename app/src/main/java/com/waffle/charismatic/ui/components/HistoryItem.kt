package com.waffle.charismatic.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.waffle.charismatic.data.response.HistoryData
import com.waffle.charismatic.domain.model.Detail
import com.waffle.charismatic.ui.navigation.Screen
import com.waffle.charismatic.ui.theme.blue
import com.waffle.charismatic.ui.theme.bodyLarge
import com.waffle.charismatic.ui.theme.bodyMedium
import com.waffle.charismatic.ui.theme.green
import com.waffle.charismatic.ui.theme.red
import com.waffle.charismatic.ui.theme.white
import com.waffle.charismatic.ui.theme.yellow

@Composable
fun HistoryItem(modifier: Modifier = Modifier, data : HistoryData, navController: NavController) {
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)
        .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onPrimary,
            shape = RoundedCornerShape(8.dp)
        ).clickable {
            val detail = Detail(data.id ?: 0, data.title ?: "")
            val json = Uri.encode(Gson().toJson(detail))
            var route = when(data.type){
                "video" -> Screen.StoryboardDetail.route.replace(Screen.Values.DetailValue, json)
                "copywriting" -> Screen.CopyWrittingDetail.route.replace(Screen.Values.DetailValue, json)
                "product_image" -> Screen.EditImageListDetail.route.replace(Screen.Values.DetailValue, json)
                else -> Screen.Home.route
            }
            navController.navigate(route) }
    ) {
        Text(text = data.title ?: "", color = MaterialTheme.colorScheme.secondary, style = bodyLarge, modifier = Modifier.padding(16.dp).align(Alignment.CenterStart))

        Box(modifier = Modifier.padding(16.dp).align(Alignment.CenterEnd)) {
            Text(text = data.type ?: "", style = bodyMedium, color = MaterialTheme.colorScheme.background, modifier = Modifier.background(
                colorSelector(data.type ?: ""), RoundedCornerShape(4.dp)).padding(8.dp))
        }

    }
}

fun colorSelector(type: String) : Color {
    return when (type) {
        "video" -> green
        "copywriting" -> yellow
        "product_image" -> blue
        else -> red
    }
}