package com.waffle.charismatic.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class CopywrittingResponse(

	@field:SerializedName("data")
	val data: CopywrittingData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class CopywrittingData(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("superiority")
	val superiority: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("product_image")
	val productImage: String? = null,

	@field:SerializedName("brand_name")
	val brandName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("market_target")
	val marketTarget: String? = null
) : Parcelable
