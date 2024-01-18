package com.waffle.charismatic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.waffle.charismatic.ui.navigation.MainNavigation
import com.waffle.charismatic.ui.screens.home.storyboard.StoryboardLayout
import com.waffle.charismatic.ui.screens.login.LoginLayout
import com.waffle.charismatic.ui.theme.CharismaticTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CharismaticTheme {
                val navHostController = rememberNavController()
                MainNavigation(
                    navHostController = navHostController
                )
            }
        }
    }
}