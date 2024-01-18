package com.waffle.charismatic

import android.app.Application
import com.waffle.charismatic.module.networkModule
import com.waffle.charismatic.module.repositoryModule
import com.waffle.charismatic.module.useCaseModule
import com.waffle.charismatic.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(networkModule, viewModelModule, repositoryModule, useCaseModule))
        }
    }
}