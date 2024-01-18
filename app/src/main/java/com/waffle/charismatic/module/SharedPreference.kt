package com.waffle.charismatic.module

import android.annotation.SuppressLint
import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SharedPreference(val context: Context) {
    companion object {
        private const val PREF_NAME ="waffle.charismatic"
        private const val USER_TOKEN = "user.token"
        private const val USER_NAME = "user.name"
        private const val USER_EMAIL = "user.email"
        private const val USER_IMAGE = "user.image"
        private const val IS_LOGIN = "is.login"
    }
    @SuppressLint("NewApi")
    private val masterKeyAlias= MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    private val pref = EncryptedSharedPreferences.create(context,PREF_NAME,masterKeyAlias,EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

    var userToken : String
        get() = pref.getString(USER_TOKEN,"").toString()
        set(value) = pref.edit().putString(USER_TOKEN,value).apply()

    var isLogin : Boolean
        get() = pref.getBoolean(IS_LOGIN, false)
        set(value) = pref.edit().putBoolean(IS_LOGIN,value).apply()

    var userName : String
        get() = pref.getString(USER_NAME,"").toString()
        set(value) = pref.edit().putString(USER_NAME,value).apply()

    var userEmail : String
        get() = pref.getString(USER_EMAIL,"").toString()
        set(value) = pref.edit().putString(USER_EMAIL,value).apply()

    var userImage : String
        get() = pref.getString(USER_IMAGE,"").toString()
        set(value) = pref.edit().putString(USER_IMAGE,value).apply()

    fun resetSharedPref(){
        context.getSharedPreferences(PREF_NAME,0).edit().clear().apply()
    }

}