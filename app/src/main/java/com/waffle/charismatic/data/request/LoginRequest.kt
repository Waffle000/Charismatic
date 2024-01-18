package com.waffle.charismatic.data.request

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class LoginRequest(

	@field:SerializedName("id_token")
	val accessToken: String? = null
) : Parcelable
