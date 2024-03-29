package com.waffle.charismatic.module

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.google.gson.GsonBuilder
import com.waffle.charismatic.api.ApiService
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module{
    single{
        val context: Context = androidContext()
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor(context))
            .addInterceptor(logging)
            .connectTimeout(60L, TimeUnit.MINUTES)
            .readTimeout(60L, TimeUnit.MINUTES)
            .writeTimeout(60L, TimeUnit.MINUTES)
            .build()
    }
    single{
        val gson = GsonBuilder()
            .setLenient()
            .create()
        Retrofit.Builder()
            .baseUrl("https://charismatic-api.niwabi.my.id/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(get())
            .build()
    }
    single{
        createWebService<ApiService>(get())
    }
}
inline fun <reified T>createWebService(retrofit: Retrofit):T = retrofit.create(T::class.java)

