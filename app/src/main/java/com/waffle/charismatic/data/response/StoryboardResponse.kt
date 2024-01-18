package com.waffle.charismatic.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class StoryboardResponse(

	@field:SerializedName("data")
	val data: StoryboardData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class StoryboardData(

	@field:SerializedName("duration")
	val duration: Int? = null,

	@field:SerializedName("product_type")
	val productType: String? = null,

	@field:SerializedName("superiority")
	val superiority: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("scenes")
	val scenes: List<ScenesItem?>? = null,

	@field:SerializedName("brand_name")
	val brandName: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("market_target")
	val marketTarget: String? = null,

	@field:SerializedName("product_title")
	val productTitle: String? = null
) : Parcelable

@Parcelize
data class ScenesItem(

	@field:SerializedName("sequence")
	val sequence: Int? = null,

	@field:SerializedName("illustration_url")
	val illustrationUrl: String? = null,

	@field:SerializedName("voice_url")
	val voiceUrl: String? = null,

	@field:SerializedName("narration")
	val narration: String? = null,

	@field:SerializedName("illustration")
	val illustration: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("video_project_id")
	val videoProjectId: Int? = null
) : Parcelable
