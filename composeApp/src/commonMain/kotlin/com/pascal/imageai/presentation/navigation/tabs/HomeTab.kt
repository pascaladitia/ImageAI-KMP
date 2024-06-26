package com.pascal.imageai.presentation.navigation.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.pascal.imageai.presentation.screen.home.HomeScreen
import kotlin.jvm.Transient

class HomeTab(
    @Transient
    val onNavigator : (isRoot : Boolean) -> Unit,
) : Tab {
    @Composable
    override fun Content() {
        Navigator(HomeScreen()) { navigator ->
            LaunchedEffect(navigator.lastItem){
                onNavigator(navigator.lastItem is HomeScreen)
            }
            SlideTransition(navigator = navigator)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Home)
            val title = "Home"
            val index: UShort = 0u

            return TabOptions(
                index, title, icon
            )
        }
}