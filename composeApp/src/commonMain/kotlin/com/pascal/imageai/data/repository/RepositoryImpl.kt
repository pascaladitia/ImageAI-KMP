package com.pascal.imageai.data.repository

import com.pascal.imageai.domain.model.ImageResponse

interface RepositoryImpl {
    suspend fun textToImage(text: String) : ImageResponse
}