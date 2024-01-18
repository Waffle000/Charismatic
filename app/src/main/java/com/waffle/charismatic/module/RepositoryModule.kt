package com.waffle.charismatic.module

import com.waffle.charismatic.data.AppRepository
import com.waffle.charismatic.domain.repository.FeatureRepository
import com.waffle.charismatic.domain.repository.UserRepository
import org.koin.dsl.bind
import org.koin.dsl.factory
import org.koin.dsl.module

val repositoryModule = module {
    // Provide AppRepository
    single { AppRepository(get()) }

    factory<UserRepository> { get<AppRepository>() }
    factory<FeatureRepository> { get<AppRepository>() }
}