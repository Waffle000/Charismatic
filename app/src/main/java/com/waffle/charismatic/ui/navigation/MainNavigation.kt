package com.waffle.charismatic.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.waffle.charismatic.data.response.CopywrittingResponse
import com.waffle.charismatic.domain.model.Detail
import com.waffle.charismatic.ui.screens.home.HomeScreen
import com.waffle.charismatic.ui.screens.home.HomeViewModel
import com.waffle.charismatic.ui.screens.home.copywritting.CopywrittingDetailLayout
import com.waffle.charismatic.ui.screens.home.copywritting.CopywrittingScreen
import com.waffle.charismatic.ui.screens.home.copywritting.CopywrittingViewModel
import com.waffle.charismatic.ui.screens.home.edit_image.EditImageDetailLayout
import com.waffle.charismatic.ui.screens.home.edit_image.EditImageDetailListLayout
import com.waffle.charismatic.ui.screens.home.edit_image.EditImageScreen
import com.waffle.charismatic.ui.screens.home.edit_image.EditImageViewModel
import com.waffle.charismatic.ui.screens.home.history.HistoryScreen
import com.waffle.charismatic.ui.screens.home.history.HistoryViewModel
import com.waffle.charismatic.ui.screens.home.storyboard.StoryboardDetailLayout
import com.waffle.charismatic.ui.screens.home.storyboard.StoryboardScreen
import com.waffle.charismatic.ui.screens.home.storyboard.StoryboardViewModel
import com.waffle.charismatic.ui.screens.login.LoginScreen
import com.waffle.charismatic.ui.screens.login.LoginViewModel
import com.waffle.charismatic.ui.screens.profile.ProfileScreen
import com.waffle.charismatic.ui.screens.profile.ProfileViewModel
import com.waffle.charismatic.ui.screens.splash.SplashScreen
import com.waffle.charismatic.utils.CopywrittingAssetParamType
import com.waffle.charismatic.utils.DetailAssetParamType
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainNavigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route,
        route = MAIN_GRAPH_ROUTE
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navHostController)
        }
        composable(Screen.Login.route) {
            val viewModel = koinViewModel<LoginViewModel>()
            val state by viewModel.uiState.collectAsState()

            LoginScreen(
                navController = navHostController,
                uiState = state,
                onLoginIntent = { intent ->
                    viewModel.processIntent(intent)
                })
        }
        composable(Screen.Home.route) {
            val viewModel = koinViewModel<HomeViewModel>()
            val state by viewModel.uiState.collectAsState()

            HomeScreen(navController = navHostController, uiState = state)
        }
        composable(Screen.Profile.route) {
            val viewModel = koinViewModel<ProfileViewModel>()
            val state by viewModel.uiState.collectAsState()

            ProfileScreen(navController = navHostController, uiState = state, onProfileIntent = {intent ->
                viewModel.processIntent(intent)
            })
        }
        composable(Screen.Storyboard.route) {
            val viewModel = koinViewModel<StoryboardViewModel>()
            val state by viewModel.uiState.collectAsState()

            StoryboardScreen(
                navController = navHostController,
                uiState = state,
                onStoryboardIntent = { intent ->
                    viewModel.processIntent(intent)
                })
        }
        composable(
            Screen.StoryboardDetail.route, arguments = listOf(
                navArgument(Screen.Values.DetailValue) {
                    type = DetailAssetParamType()
                })
        ) {
            val viewModel = koinViewModel<StoryboardViewModel>()
            val state by viewModel.uiState.collectAsState()
            val detail = it.arguments?.getParcelable<Detail>(Screen.Values.DetailValue) ?: Detail(
                0,
                "Storyboard Detail"
            )
            StoryboardDetailLayout(
                detail = detail,
                navController = navHostController,
                uiState = state,
                onStoryboardIntent = { intent ->
                    viewModel.processIntent(intent)
                }
            )
        }
        composable(Screen.CopyWritting.route) {
            val viewModel = koinViewModel<CopywrittingViewModel>()
            val state by viewModel.uiState.collectAsState()

            CopywrittingScreen(
                navController = navHostController,
                uiState = state,
                onCopywrittingIntent = { intent ->
                    viewModel.processIntent(intent)
                })
        }
        composable(Screen.CopyWrittingDetail.route, arguments = listOf(
            navArgument(Screen.Values.DetailValue) {
                type = DetailAssetParamType()
            })) {
            val viewModel = koinViewModel<CopywrittingViewModel>()
            val state by viewModel.uiState.collectAsState()
            val detail = it.arguments?.getParcelable<Detail>(Screen.Values.DetailValue) ?: Detail(
                0,
                "Copywritting Detail"
            )
            CopywrittingDetailLayout(
                detail = detail,
                navController = navHostController,
                uiState = state,
                onCopywrittingIntent = {intent ->
                    viewModel.processIntent(intent)
                }
            )
        }
        composable(Screen.EditImage.route) {
            val viewModel = koinViewModel<EditImageViewModel>()
            val state by viewModel.uiState.collectAsState()

            EditImageScreen(navController = navHostController, uiState = state, onEditImageIntent = {intent ->
                viewModel.processIntent(intent)
            })
        }
        composable(Screen.EditImageDetail.route, arguments = listOf(
            navArgument(Screen.Values.DetailValue) {
                type = DetailAssetParamType()
            })) {
            val viewModel = koinViewModel<EditImageViewModel>()
            val state by viewModel.uiState.collectAsState()
            val detail = it.arguments?.getParcelable<Detail>(Screen.Values.DetailValue) ?: Detail(
                0,
                "Edit Image Detail"
            )
            EditImageDetailLayout(detail = detail, navController = navHostController, uiState = state, onEditImageIntent = {intent ->
                viewModel.processIntent(intent)
            })
        }
        composable(Screen.EditImageListDetail.route, arguments = listOf(
            navArgument(Screen.Values.DetailValue) {
                type = DetailAssetParamType()
            })) {
            val viewModel = koinViewModel<EditImageViewModel>()
            val state by viewModel.uiState.collectAsState()
            val detail = it.arguments?.getParcelable<Detail>(Screen.Values.DetailValue) ?: Detail(
                0,
                "Edit Image List"
            )
            EditImageDetailListLayout(detail = detail, navController = navHostController, uiState = state, onEditImageIntent = {intent ->
                viewModel.processIntent(intent)
            })
        }
        composable(Screen.History.route) {
            val viewModel = koinViewModel<HistoryViewModel>()
            val state by viewModel.uiState.collectAsState()

            HistoryScreen(navController = navHostController, uiState = state, onHistoryIntent = { intent ->
                viewModel.processIntent(intent)
            })
        }
    }
}