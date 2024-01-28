package com.waffle.charismatic.ui.screens.home.copywritting

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CopyAll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.waffle.charismatic.R
import com.waffle.charismatic.data.response.CopywrittingResponse
import com.waffle.charismatic.domain.model.Detail
import com.waffle.charismatic.ui.components.EditTextForm
import com.waffle.charismatic.ui.components.UniversalTopBar
import com.waffle.charismatic.ui.navigation.Screen
import com.waffle.charismatic.ui.theme.CharismaticTheme
import com.waffle.charismatic.ui.theme.bodyMedium
import com.waffle.charismatic.ui.theme.bodySmall

@Composable
fun CopywrittingDetailLayout(
    modifier: Modifier = Modifier, detail: Detail, navController: NavController,
    uiState: UiState,
    onCopywrittingIntent: (CopywrittingIntent) -> Unit
) {
        LaunchedEffect(key1 = detail.id, key2 = true) {
            onCopywrittingIntent(CopywrittingIntent.GetCopywrittingDetail(detail.id))
        }
    
    val clipboardManager: ClipboardManager = LocalClipboardManager.current


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier.align(Alignment.Center), color = MaterialTheme.colorScheme.onPrimary)
        }
        Column {
            UniversalTopBar(name = detail.name ?: "", navController = navController)
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .weight(1f)
            ) {
                Text(
                    text = uiState.isSuccessDetail.data?.result ?: "Empty Result",
                    color = MaterialTheme.colorScheme.secondary,
                    style = bodySmall,
                    modifier = Modifier
                        .padding(8.dp)
                        .verticalScroll(rememberScrollState()),
                )


                Icon(
                    Icons.Outlined.CopyAll,
                    contentDescription = "Icon Copy",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                        .clickable(onClick = {

                            clipboardManager.setText(
                                AnnotatedString(
                                    (uiState.isSuccessDetail.data?.result ?: "Empty Result")
                                )
                            )
                        }),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
//            Button(
//                onClick = { }, modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                Text(
//                    "Save",
//                    color = MaterialTheme.colorScheme.onSecondary,
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
        }
    }
}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview()
//@Composable
//private fun CopywrittingDetailScreenPreview() {
//    CharismaticTheme {
//        CopywrittingDetailLayout()
//    }
//}