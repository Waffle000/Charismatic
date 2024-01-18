package com.waffle.charismatic.ui.screens.home.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waffle.charismatic.data.request.StoryboardRequest
import com.waffle.charismatic.data.response.HistoryResponse
import com.waffle.charismatic.domain.usecase.FeatureUseCase
import com.waffle.charismatic.ui.screens.home.edit_image.EditImageIntent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HistoryViewModel(private val featureUseCase: FeatureUseCase) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    fun processIntent(intent: HistoryIntent) {
        when (intent) {
            is HistoryIntent.GetHistory -> getHistory()
        }
    }

    fun getHistory() {
        CoroutineScope(Dispatchers.Main).launch {
            featureUseCase.getHistory()
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


    private fun showLoading() {
        _uiState.value = UiState(isLoading = true)
    }

    private fun hideLoading() {
        _uiState.value = UiState(isLoading = false)
    }
}
data class UiState(
    val isLoading: Boolean = false,
    val isSuccess: HistoryResponse = HistoryResponse(),
    val isError: String = ""
)

sealed interface HistoryIntent {
    object GetHistory : HistoryIntent
}