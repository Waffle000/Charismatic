package com.waffle.charismatic.data

import com.waffle.charismatic.api.ApiService
import com.waffle.charismatic.data.request.LoginRequest
import com.waffle.charismatic.data.request.StoryboardRequest
import com.waffle.charismatic.data.response.CopywrittingResponse
import com.waffle.charismatic.data.response.EditImageDetailResponse
import com.waffle.charismatic.data.response.EditImageListDetailResponse
import com.waffle.charismatic.data.response.EditImageResponse
import com.waffle.charismatic.data.response.HistoryResponse
import com.waffle.charismatic.data.response.LoginResponse
import com.waffle.charismatic.data.response.StoryboardResponse
import com.waffle.charismatic.domain.repository.FeatureRepository
import com.waffle.charismatic.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AppRepository(private val apiService: ApiService) : UserRepository, FeatureRepository {

    override fun postLogin(loginRequest: LoginRequest): Flow<Result<LoginResponse>> {
        return flow {
            val response = apiService.postLogin(loginRequest).body()
            if (response != null) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(Throwable(message = "Empty Data")))
            }
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    override fun postStoryboard(storyboardRequest: StoryboardRequest): Flow<Result<StoryboardResponse>> {
        return flow {
            val response = apiService.postStoryboard(storyboardRequest).body()
            if (response != null) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(Throwable(message = "Empty Data")))
            }
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    override fun getStoryboardDetail(id: Int): Flow<Result<StoryboardResponse>> {
        return flow {
            val response = apiService.getStoryboardDetail(id).body()
            if (response != null) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(Throwable(message = "Empty Data")))
            }
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun postCopywritting(
        title: RequestBody,
        brandName: RequestBody,
        productType: RequestBody,
        marketTarget: RequestBody,
        superiority: RequestBody,
        file: MultipartBody.Part?
    ): Flow<Result<CopywrittingResponse>> {
        return flow {
            val response =
                apiService.postCopywritting(title, brandName, productType, marketTarget, superiority, file)
                    .body()
            if (response != null) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(Throwable(message = "Empty Data")))
            }
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    override fun getCopywrittingDetail(id: Int): Flow<Result<CopywrittingResponse>> {
        return flow {
            val response = apiService.getCopywrittingDetail(id).body()
            if (response != null) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(Throwable(message = "Empty Data")))
            }
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    override fun postCreateEditImage(
        prompt: RequestBody,
        title: RequestBody,
        image: MultipartBody.Part?,
        mask: MultipartBody.Part?
    ): Flow<Result<EditImageResponse>> {
        return flow {
            val response = apiService.postCreateEditImage(prompt, title, image, mask).body()
            if (response != null) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(Throwable(message = "Empty Data")))
            }
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    override fun postGenerateEditImage(id: Int, prompt: RequestBody): Flow<Result<EditImageDetailResponse>> {
        return flow {
            val response = apiService.postGenerateEditImage(id, prompt).body()
            if (response != null) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(Throwable(message = "Empty Data")))
            }
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    override fun getEditImageListDetail(id: Int): Flow<Result<EditImageListDetailResponse>> {
        return flow {
            val response = apiService.getEditImageListDetail(id).body()
            if (response != null) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(Throwable(message = "Empty Data")))
            }
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    override fun getEditImageDetail(id: Int): Flow<Result<EditImageDetailResponse>> {
        return flow {
            val response = apiService.getEditImageDetail(id).body()
            if (response != null) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(Throwable(message = "Empty Data")))
            }
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    override fun getHistory(): Flow<Result<HistoryResponse>> {
        return flow {
            val response = apiService.getHistory().body()
            if (response != null) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(Throwable(message = "Empty Data")))
            }
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }
}