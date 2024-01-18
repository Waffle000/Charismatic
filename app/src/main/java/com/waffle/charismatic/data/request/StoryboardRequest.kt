package com.waffle.charismatic.data.request

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class StoryboardRequest(

	@field:SerializedName("duration")
	val duration: Int? = null,

	@field:SerializedName("product_type")
	val productType: String? = null,

	@field:SerializedName("superiority")
	val superiority: String? = null,

	@field:SerializedName("brand_name")
	val brandName: String? = null,

	@field:SerializedName("market_target")
	val marketTarget: String? = null,

	@field:SerializedName("product_title")
	val productTitle: String? = null
) : Parcelable
