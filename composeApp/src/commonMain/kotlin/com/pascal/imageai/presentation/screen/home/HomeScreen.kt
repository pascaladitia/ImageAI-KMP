package com.pascal.imageai.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.pascal.imageai.domain.usecases.UiState
import com.pascal.imageai.presentation.component.ErrorScreen
import com.pascal.imageai.presentation.component.LoadingScreen
import org.koin.compose.koinInject

class HomeScreen() : Screen {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = koinInject<HomeViewModel>()
        val navigator = LocalNavigator.current
        val uiState by viewModel.result.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.textToImage("android")
        }

        when (uiState) {
            is UiState.Empty -> {}
            is UiState.Loading -> {
                LoadingScreen()
            }
            is UiState.Error -> {
                val message = (uiState as UiState.Error).message
                ErrorScreen(message = message) {}
            }
            is UiState.Success -> {
                val data = (uiState as UiState.Success).data
            }
        }
    }
}