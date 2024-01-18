package com.waffle.charismatic.ui.screens.home.storyboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waffle.charismatic.data.request.StoryboardRequest
import com.waffle.charismatic.data.response.StoryboardResponse
import com.waffle.charismatic.domain.usecase.FeatureUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class StoryboardViewModel(private val featureUseCase: FeatureUseCase) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    fun processIntent(intent: StoryboardIntent) {
        when (intent) {
            is StoryboardIntent.PostStoryboard -> postStoryboard(intent.storyboardRequest)
            is StoryboardIntent.GetStoryboardDetail -> getStoryboardDetail(intent.id)
        }
    }

    private fun getStoryboardDetail(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            featureUseCase.getStoryboardDetail(id)
                .onStart {
                    showLoading()
                }
                .catch { err ->
                    hideLoading()
                    _uiState.value = UiState(isError = err.message ?: "Something Error")
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

    private fun postStoryboard(storyboardRequest: StoryboardRequest) {
        CoroutineScope(Dispatchers.Main).launch {
            featureUseCase.postStoryboard(storyboardRequest)
                .onStart {
                    showLoading()
                }
                .catch { err ->
                    hideLoading()
                    _uiState.value = UiState(isError = err.message ?: "Something Error")
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

    private fun showLoading() {
        _uiState.value = UiState(isLoading = true)
    }

    private fun hideLoading() {
        _uiState.value = UiState(isLoading = false)
    }
}
data class UiState(
    val isLoading: Boolean = false,
    val isSuccess: StoryboardResponse = StoryboardResponse(),
    val isSuccessDetail: StoryboardResponse = StoryboardResponse(),
    val isError: String = ""
)

sealed interface StoryboardIntent {
    data class PostStoryboard(val storyboardRequest: StoryboardRequest) : StoryboardIntent
    data class GetStoryboardDetail(val id: Int) : StoryboardIntent
}