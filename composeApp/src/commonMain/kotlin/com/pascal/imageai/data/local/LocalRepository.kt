package com.pascal.imageai.data.local

import com.pascal.imageai.db.MyDatabase
import org.koin.core.annotation.Single
import sqldelight.db.ProfileEntity

@Single
class LocalRepository(
    db: MyDatabase,
) : LocalRepositoryImpl {

    private val profile = db.profileEntityQueries

    override suspend fun getProfileById(id: Long): ProfileEntity? {
        return profile.getProfileById(id).executeAsOneOrNull()
    }

    override fun getAllProfiles(): List<ProfileEntity> {
        return profile.getAllProfiles().executeAsList()
    }

    override suspend fun deleteProfileById(id: Long) {
        return profile.deleteProfileById(id)
    }

    override suspend fun insertProfile(item: ProfileEntity) {
        return profile.insertProfiles(
            id = item.id,
            name = item.name,
            imagePath = item.imagePath,
            imageProfilePath = item.imageProfilePath,
            email = item.email,
            phone = item.phone,
            address = item.address,
        )
    }
}