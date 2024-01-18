package com.waffle.charismatic.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class EditImageResponse(

	@field:SerializedName("data")
	val data: EditImageData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class EditImageData(

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("prompt")
	val prompt: String? = null,

	@field:SerializedName("product_image_id")
	val productImageId: Int? = null
) : Parcelable
