package com.waffle.charismatic.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.waffle.charismatic.data.response.CopywrittingResponse

class CopywrittingAssetParamType : NavType<CopywrittingResponse>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): CopywrittingResponse? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): CopywrittingResponse {
        return Gson().fromJson(value.substringAfter("{").substringBeforeLast("}"), CopywrittingResponse::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: CopywrittingResponse) {
        bundle.putParcelable(key, value)
    }
}