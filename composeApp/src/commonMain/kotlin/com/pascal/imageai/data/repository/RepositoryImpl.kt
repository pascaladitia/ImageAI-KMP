package com.pascal.imageai.data.repository

import com.pascal.imageai.domain.model.TextToImage

interface RepositoryImpl {
    suspend fun textToImage(text: String) : TextToImage
}