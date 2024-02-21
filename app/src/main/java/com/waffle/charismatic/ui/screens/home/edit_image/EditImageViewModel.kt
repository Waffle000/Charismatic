package com.waffle.charismatic.ui.screens.home.edit_image

import androidx.lifecycle.ViewModel
import com.waffle.charismatic.data.response.EditImageDetailResponse
import com.waffle.charismatic.data.response.EditImageListDetailResponse
import com.waffle.charismatic.data.response.EditImageResponse
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

class EditImageViewModel(private val featureUseCase: FeatureUseCase) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    fun processIntent(intent: EditImageIntent) {
        when (intent) {
            is EditImageIntent.PostCreateEditImage -> postCreateEditImage(intent.prompt, intent.title, intent.image)
            is EditImageIntent.PostGenerateEditImage -> postGenerateEditImage(intent.id, intent.prompt)
            is EditImageIntent.GetEditImageListDetail -> getEditImageListDetail(intent.id)
            is EditImageIntent.GetEditImageDetail -> getEditImageDetail(intent.id)
        }
    }

    private fun postCreateEditImage(
        prompt: RequestBody,
        title: RequestBody,
        image: MultipartBody.Part?
    ) {
        CoroutineScope(Dispatchers.Main).launch {
                featureUseCase.postCreateEditImage(prompt, title, image)
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
                                UiState(isSuccessSingle = data)
                        }, onFailure = {
                            _uiState.value =
                                UiState(
                                    isError = "Something Error"
                                )

                        })

                    }
        }
    }

    private fun postGenerateEditImage(
        id:Int,
        prompt: RequestBody
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            featureUseCase.postGenerateEditImage(id, prompt)
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
                            UiState(isSuccessSingleDetail = data)
                    }, onFailure = {
                        _uiState.value =
                            UiState(
                                isError = "Something Error"
                            )

                    })

                }
        }
    }

    private fun getEditImageListDetail(
        id:Int
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            featureUseCase.getEditImageListDetail(id)
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
                            UiState(isSuccessMultiple = data)
                    }, onFailure = {
                        _uiState.value =
                            UiState(
                                isError = "Something Error"
                            )

                    })

                }
        }
    }

    private fun getEditImageDetail(
        id:Int
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            featureUseCase.getEditImageDetail(id)
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
                            UiState(isSuccessSingleDetail = data)
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
    val isSuccessSingle: EditImageResponse = EditImageResponse(),
    val isSuccessSingleDetail: EditImageDetailResponse = EditImageDetailResponse(),
    val isSuccessMultiple: EditImageListDetailResponse = EditImageListDetailResponse(),
    val isError: String = ""
)

sealed interface EditImageIntent {
    data class PostCreateEditImage(
        val prompt: RequestBody,
        val title: RequestBody,
        val image: MultipartBody.Part?
    ) : EditImageIntent

    data class PostGenerateEditImage(val id: Int, val prompt: RequestBody) : EditImageIntent
    data class GetEditImageListDetail(val id: Int) : EditImageIntent
    data class GetEditImageDetail(val id: Int) : EditImageIntent
}