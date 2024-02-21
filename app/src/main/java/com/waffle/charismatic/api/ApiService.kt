package com.waffle.charismatic.api

import com.waffle.charismatic.data.request.LoginRequest
import com.waffle.charismatic.data.request.StoryboardRequest
import com.waffle.charismatic.data.response.CopywrittingResponse
import com.waffle.charismatic.data.response.EditImageDetailResponse
import com.waffle.charismatic.data.response.EditImageListDetailResponse
import com.waffle.charismatic.data.response.EditImageResponse
import com.waffle.charismatic.data.response.HistoryResponse
import com.waffle.charismatic.data.response.LoginResponse
import com.waffle.charismatic.data.response.StoryboardResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @POST("v1/auth/login/google/id-token")
    suspend fun postLogin(
        @Body loginRequest: LoginRequest
    ) : Response<LoginResponse>

    @POST("v1/video-project/generate-sync")
    suspend fun postStoryboard(
        @Body storyboardRequest: StoryboardRequest
    ) : Response<StoryboardResponse>

    @GET("v1/video-project/detail/{id}")
    suspend fun getStoryboardDetail(
        @Path("id") id: Int
    ) : Response<StoryboardResponse>

    @Multipart
    @POST("v1/copywriting-project/create-sync")
    suspend fun postCopywritting(
        @Part("title") title: RequestBody,
        @Part("brand_name") brandName: RequestBody,
        @Part("product_type") productType : RequestBody,
        @Part("market_target") marketTarget: RequestBody,
        @Part("superiority") superiority: RequestBody
    ) : Response<CopywrittingResponse>

    @GET("v1/copywriting-project/detail/{id}")
    suspend fun getCopywrittingDetail(
        @Path("id") id: Int
    ) : Response<CopywrittingResponse>

    @Multipart
    @POST("v1/product-image/create-sync")
    suspend fun postCreateEditImage(
        @Part("prompt") prompt: RequestBody,
        @Part("title") title: RequestBody,
        @Part image: MultipartBody.Part?
    ) : Response<EditImageResponse>

    @Multipart
    @POST("v1/product-image/detail/{id}/create-sync")
    suspend fun postGenerateEditImage(
        @Path("id") id : Int,
        @Part("prompt") prompt: RequestBody
    ) : Response<EditImageDetailResponse>

    @GET("v1/product-image/detail/{id}")
    suspend fun getEditImageListDetail(
        @Path("id") id : Int
    ) : Response<EditImageListDetailResponse>

    @GET("v1/product-image/generated/{id}")
    suspend fun getEditImageDetail(
        @Path("id") id : Int
    ) : Response<EditImageDetailResponse>

    @GET("v1/history")
    suspend fun getHistory() : Response<HistoryResponse>
}