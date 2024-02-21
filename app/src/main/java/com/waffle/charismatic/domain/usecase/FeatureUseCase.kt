package com.waffle.charismatic.domain.usecase

import com.waffle.charismatic.data.request.StoryboardRequest
import com.waffle.charismatic.data.response.CopywrittingResponse
import com.waffle.charismatic.data.response.EditImageDetailResponse
import com.waffle.charismatic.data.response.EditImageListDetailResponse
import com.waffle.charismatic.data.response.EditImageResponse
import com.waffle.charismatic.data.response.HistoryResponse
import com.waffle.charismatic.data.response.StoryboardResponse
import com.waffle.charismatic.domain.repository.FeatureRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class FeatureUseCase(private val featureRepository: FeatureRepository) {
    fun postStoryboard(storyboardRequest: StoryboardRequest) : Flow<Result<StoryboardResponse>> {
        return featureRepository.postStoryboard(storyboardRequest)
    }

    fun getStoryboardDetail(id: Int): Flow<Result<StoryboardResponse>> {
        return featureRepository.getStoryboardDetail(id)
    }

    suspend fun postCopywritting(title: RequestBody,
                         brandName: RequestBody,
                         productType : RequestBody,
                         marketTarget: RequestBody,
                         superiority: RequestBody) : Flow<Result<CopywrittingResponse>> {
        return featureRepository.postCopywritting(title, brandName, productType, marketTarget, superiority)
    }

    fun getCopywrittingDetail(
        id: Int
    ) : Flow<Result<CopywrittingResponse>> {
        return featureRepository.getCopywrittingDetail(id)
    }

    fun postCreateEditImage(
        prompt: RequestBody,
        title: RequestBody,
        image: MultipartBody.Part?
    ) : Flow<Result<EditImageResponse>> {
        return featureRepository.postCreateEditImage(prompt, title, image)
    }

    fun postGenerateEditImage(
        id: Int,
        prompt: RequestBody
    ) : Flow<Result<EditImageDetailResponse>> {
        return featureRepository.postGenerateEditImage(id, prompt)
    }

    fun getEditImageListDetail(
        id: Int
    ) : Flow<Result<EditImageListDetailResponse>> {
        return featureRepository.getEditImageListDetail(id)
    }

    fun getEditImageDetail(
        id: Int
    ) : Flow<Result<EditImageDetailResponse>> {
        return featureRepository.getEditImageDetail(id)
    }


    fun getHistory() : Flow<Result<HistoryResponse>> {
        return featureRepository.getHistory()
    }
}