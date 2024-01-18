package com.waffle.charismatic.module

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${SharedPreference(context).userToken}")
                .build()
        )
    }
}

