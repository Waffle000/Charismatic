package com.waffle.charismatic.ui.screens.home.copywritting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waffle.charismatic.data.request.StoryboardRequest
import com.waffle.charismatic.data.response.CopywrittingResponse
import com.waffle.charismatic.domain.usecase.FeatureUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class CopywrittingViewModel(private val featureUseCase: FeatureUseCase) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    fun processIntent(intent: CopywrittingIntent) {
        when (intent) {
            is CopywrittingIntent.PostCopywritting -> postCopywritting(
                intent.title,
                intent.brandName,
                intent.productType,
                intent.marketTarget,
                intent.superiority
            )


            is CopywrittingIntent.GetCopywrittingDetail -> getCopywrittingDetail(intent.id)
        }
    }

    fun postCopywritting(
        title: RequestBody,
        brandName: RequestBody,
        productType: RequestBody,
        marketTarget: RequestBody,
        superiority: RequestBody
    ) {
        CoroutineScope(Dispatchers.Main).launch {

            featureUseCase.postCopywritting(
                title,
                brandName,
                productType,
                marketTarget,
                superiority
            )
                .onStart {
                    showLoading()
                }
                .catch { err ->
                    hideLoading()
                    _uiState.value = UiState(
                        isError = err.message ?: "Something Error"
                    )
                }
                .collect { result ->
                    hideLoading()
                    result.fold(onSuccess = { data ->
                        _uiState.value =
                            UiState(isSuccess = data)
                    }, onFailure = {
                        _uiState.value =
                            UiState(
                                isError = "Something Error"
                            )
                    })
                }

        }
    }

    private fun getCopywrittingDetail(
        id: Int
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            featureUseCase.getCopywrittingDetail(id)
                .onStart {
                    showLoading()
                }
                .catch { err ->
                    hideLoading()
                    _uiState.value = UiState(
                        isError = err.message ?: "Something Error"
                    )
                }
                .collect { result ->
                    hideLoading()
                    result.fold(onSuccess = { data ->
                        _uiState.value =
                            UiState(isSuccessDetail = data)
                    }, onFailure = {
                        _uiState.value =
                            UiState(
                                isError = "Something Error"
                            )

                    })

                }


        }
    }

    private fun showLoading() {
        _uiState.value = UiState(isLoading = true)
    }

    private fun hideLoading() {
        _uiState.value = UiState(isLoading = false)
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val isSuccess: CopywrittingResponse = CopywrittingResponse(),
    val isSuccessDetail: CopywrittingResponse = CopywrittingResponse(),
    val isError: String = ""
)

sealed interface CopywrittingIntent {
    data class PostCopywritting(
        val title: RequestBody,
        val brandName: RequestBody,
        val productType: RequestBody,
        val marketTarget: RequestBody,
        val superiority: RequestBody
    ) : CopywrittingIntent

    data class GetCopywrittingDetail(val id: Int) : CopywrittingIntent
}