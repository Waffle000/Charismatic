package com.waffle.charismatic.domain.repository

import com.waffle.charismatic.data.request.StoryboardRequest
import com.waffle.charismatic.data.response.CopywrittingResponse
import com.waffle.charismatic.data.response.EditImageDetailResponse
import com.waffle.charismatic.data.response.EditImageListDetailResponse
import com.waffle.charismatic.data.response.EditImageResponse
import com.waffle.charismatic.data.response.HistoryResponse
import com.waffle.charismatic.data.response.StoryboardResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface FeatureRepository {
    fun postStoryboard(storyboardRequest: StoryboardRequest): Flow<Result<StoryboardResponse>>

    fun getStoryboardDetail(id: Int): Flow<Result<StoryboardResponse>>

    suspend fun postCopywritting(
        title: RequestBody,
        brandName: RequestBody,
        productType : RequestBody,
        marketTarget: RequestBody,
        superiority: RequestBody,
        file: MultipartBody.Part?
    ) : Flow<Result<CopywrittingResponse>>

    fun getCopywrittingDetail(
        id: Int
    ) : Flow<Result<CopywrittingResponse>>

    fun postCreateEditImage(
        prompt: RequestBody,
        title: RequestBody,
        image: MultipartBody.Part?,
        mask: MultipartBody.Part?
    ) : Flow<Result<EditImageResponse>>

    fun postGenerateEditImage(
        id: Int,
        prompt: RequestBody
    ) : Flow<Result<EditImageDetailResponse>>

    fun getEditImageListDetail(
        id: Int
    ) : Flow<Result<EditImageListDetailResponse>>

    fun getEditImageDetail(
        id: Int
    ) : Flow<Result<EditImageDetailResponse>>

    fun getHistory() : Flow<Result<HistoryResponse>>
}
