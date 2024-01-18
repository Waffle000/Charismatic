package com.waffle.charismatic.module

import com.waffle.charismatic.ui.screens.home.HomeViewModel
import com.waffle.charismatic.ui.screens.home.copywritting.CopywrittingViewModel
import com.waffle.charismatic.ui.screens.home.edit_image.EditImageViewModel
import com.waffle.charismatic.ui.screens.home.history.HistoryViewModel
import com.waffle.charismatic.ui.screens.home.storyboard.StoryboardViewModel
import com.waffle.charismatic.ui.screens.login.LoginViewModel
import com.waffle.charismatic.ui.screens.profile.ProfileViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { LoginViewModel(get()) }
    single { HomeViewModel(get()) }
    single { ProfileViewModel(get()) }
    single { StoryboardViewModel(get()) }
    single { CopywrittingViewModel(get()) }
    single { EditImageViewModel(get()) }
    single { HistoryViewModel(get()) }
}