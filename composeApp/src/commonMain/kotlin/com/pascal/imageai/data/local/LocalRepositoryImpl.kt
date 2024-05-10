package com.pascal.imageai.data.local

import sqldelight.db.ProfileEntity

interface LocalRepositoryImpl {
    suspend fun getProfileById(id: Long): ProfileEntity?
    fun getAllProfiles(): List<ProfileEntity>
    suspend fun deleteProfileById(id: Long)
    suspend fun insertProfile(item: ProfileEntity)
}