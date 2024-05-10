package com.pascal.imageai.presentation.navigation.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.pascal.imageai.presentation.screen.profile.ProfileScreen

object ProfileTab : Tab {
    @Composable
    override fun Content() {
        Navigator(ProfileScreen())
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Person)
            val title = "Profile"
            val index: UShort = 0u

            return TabOptions(
                index, title, icon
            )
        }
}