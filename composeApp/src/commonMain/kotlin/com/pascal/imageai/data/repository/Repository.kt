package com.pascal.imageai.data.repository

import com.pascal.imageai.data.remote.KtorClientApi
import com.pascal.imageai.domain.model.TextToImage
import org.koin.core.annotation.Single

@Single
class Repository : RepositoryImpl {

    override suspend fun textToImage(text: String): TextToImage {
        return KtorClientApi.textToImage(text)
    }
}