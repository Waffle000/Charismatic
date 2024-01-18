package com.waffle.charismatic.module

import com.waffle.charismatic.domain.usecase.FeatureUseCase
import com.waffle.charismatic.domain.usecase.UserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { UserUseCase(get()) }
    single { FeatureUseCase(get()) }
}