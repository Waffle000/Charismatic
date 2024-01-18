package com.waffle.charismatic.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.waffle.charismatic.domain.model.Detail

class DetailAssetParamType : NavType<Detail>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Detail? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Detail {
        return Gson().fromJson(value.substringAfter("{").substringBeforeLast("}"), Detail::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Detail) {
        bundle.putParcelable(key, value)
    }
}