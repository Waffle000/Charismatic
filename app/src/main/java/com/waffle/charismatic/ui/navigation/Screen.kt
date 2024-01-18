package com.waffle.charismatic.ui.navigation


const val MAIN_GRAPH_ROUTE = "main"

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Login: Screen("login_screen")
    object Home: Screen("home_screen")
    object Profile: Screen("profile_screen")
    object Storyboard : Screen("storyboard_screen")
    object StoryboardDetail : Screen("storyboard_detail_screen/{${Values.DetailValue}}")
    object CopyWritting : Screen("copywritting_screen")
    object CopyWrittingDetail : Screen("copywritting_detail_screen/{${Values.DetailValue}}/{${Values.VariableValue}}")
    object EditImage : Screen("editbackground_screen")
    object EditImageDetail : Screen("editbackground_detail_screen/{${Values.DetailValue}}")
    object EditImageListDetail : Screen("ditbackground_list_detail_screen/{${Values.DetailValue}}")
    object History : Screen("history_screen")

    object Values {
        const val DetailValue = "detail_value"
        const val VariableValue = "variable_value"
    }
}

