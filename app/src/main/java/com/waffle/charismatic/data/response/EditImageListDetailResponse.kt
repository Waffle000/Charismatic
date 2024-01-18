package com.waffle.charismatic.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class EditImageListDetailResponse(

	@field:SerializedName("data")
	val data: EditImageListDetailData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class GeneratedImagesItem(

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("prompt")
	val prompt: String? = null,

	@field:SerializedName("product_image_id")
	val productImageId: Int? = null
) : Parcelable

@Parcelize
data class EditImageListDetailData(

	@field:SerializedName("generated_images")
	val generatedImages: List<GeneratedImagesItem?>? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("mask_url")
	val maskUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,
) : Parcelable
