package com.pascal.imageai.presentation.screen.home

import com.pascal.imageai.data.repository.Repository
import com.pascal.imageai.domain.model.ImageResponse
import com.pascal.imageai.domain.usecases.UiState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _result = MutableStateFlow<UiState<ImageResponse>>(UiState.Empty)
    val result: StateFlow<UiState<ImageResponse>> = _result.asStateFlow()

    fun textToImage(text: String) {
        viewModelScope.launch {
            _result.value = UiState.Loading
            try {
                val response = repository.textToImage(text)
                _result.value = UiState.Success(response)
            } catch (e: Exception) {
                val error = e.message.toString()
                _result.value = UiState.Error(error)
            }

        }
    }
}