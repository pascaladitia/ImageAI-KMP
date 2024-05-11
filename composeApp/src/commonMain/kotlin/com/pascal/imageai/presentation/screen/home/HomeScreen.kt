package com.pascal.imageai.presentation.screen.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.pascal.imageai.domain.usecases.UiState
import com.pascal.imageai.presentation.component.ErrorScreen
import com.pascal.imageai.presentation.component.IconCircleBorder
import com.pascal.imageai.presentation.component.LoadingScreen
import com.pascal.imageai.presentation.component.NetworkImage
import com.pascal.imageai.presentation.component.Search
import com.pascal.imageai.theme.LocalThemeIsDark
import compose.icons.FeatherIcons
import compose.icons.feathericons.Send
import imageai_kmp.composeapp.generated.resources.Res
import imageai_kmp.composeapp.generated.resources.ic_dark_mode
import imageai_kmp.composeapp.generated.resources.ic_light_mode
import imageai_kmp.composeapp.generated.resources.theme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject

class HomeScreen() : Screen {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = koinInject<HomeViewModel>()
        val navigator = LocalNavigator.current
        val uiState by viewModel.result.collectAsState()

        when (uiState) {
            is UiState.Empty -> {
                val data = ""
                HomeContent(
                    data = data
                ) {
                    viewModel.textToImage(it)
                }
            }
            is UiState.Loading -> {
                LoadingScreen()
            }

            is UiState.Error -> {
                val message = (uiState as UiState.Error).message
                ErrorScreen(message = message) {}
            }

            is UiState.Success -> {
                var result: String? = ""
                val deepai = (uiState as UiState.Success).data.deepai?.items?.get(0)?.imageResourceUrl
                val amazon = (uiState as UiState.Success).data.deepai?.items?.get(0)?.imageResourceUrl
                val openai = (uiState as UiState.Success).data.deepai?.items?.get(0)?.imageResourceUrl
                val stabilityai = (uiState as UiState.Success).data.deepai?.items?.get(0)?.imageResourceUrl

                result = if (!deepai.isNullOrEmpty()) {
                    deepai
                } else if (!amazon.isNullOrEmpty()) {
                    amazon
                } else if (!openai.isNullOrEmpty()) {
                    openai
                } else {
                    stabilityai
                }

                HomeContent(
                    data = deepai
                ) {
                    viewModel.textToImage(it)
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    data: String?,
    onSearch: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var isDark by LocalThemeIsDark.current
    val icon = remember(isDark) {
        if (isDark) Res.drawable.ic_light_mode
        else Res.drawable.ic_dark_mode
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Generate your image",
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.width(6.dp))

            ElevatedButton(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp).widthIn(min = 200.dp),
                onClick = { isDark = !isDark },
                content = {
                    Icon(vectorResource(icon), contentDescription = null)
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(stringResource(Res.string.theme))
                }
            )
        }

        NetworkImage(
            url = data ?: "",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
        )

        Spacer(Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Search(modifier = Modifier.weight(1f)) {
                query = it
            }

            Spacer(Modifier.width(16.dp))

            IconCircleBorder(
                imageVector = FeatherIcons.Send,
                size = 48.dp,
                padding = 12.dp
            ) {
                onSearch(query)
            }
        }
    }
}