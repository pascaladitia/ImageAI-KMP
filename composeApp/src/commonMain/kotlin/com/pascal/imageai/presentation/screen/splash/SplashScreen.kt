package com.pascal.imageai.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.pascal.imageai.presentation.screen.bottom.BottomScreen
import com.pascal.imageai.presentation.screen.home.HomeScreen
import imageai_kmp.composeapp.generated.resources.Res
import imageai_kmp.composeapp.generated.resources.logo
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class SplashScreen() : Screen {
    @Composable
    override fun Content() {
        SplashContent()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashContent(
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.current

    LaunchedEffect(Unit) {
        delay(1500)
        navigator?.replace(HomeScreen())
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(Res.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(120.dp)
        )
    }
}