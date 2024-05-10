package com.pascal.imageai.data.preferences

import com.pascal.imageai.createSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import com.pascal.imageai.domain.model.ImageResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object PrefLogin {
    private const val TOKEN = "token"
    private const val IS_ADMIN = "is_admin"
    private const val IS_SAVE_LOGIN = "is_save_login"
    private const val EMAIL = "email"
    private const val PASSWORD = "password"
    private const val IMAGE_URL = "image_url"
    private const val RESPONSE_LOGIN = "response_login"

    private fun Settings.setLoginResponse(value: ImageResponse?) {
        if (value != null) {
            val jsonString = Json.encodeToString(value)
            putString(RESPONSE_LOGIN, jsonString)
        } else {
            remove(RESPONSE_LOGIN)
        }
    }

    private fun Settings.getLoginResponse(): ImageResponse? {
        val jsonString = getString(RESPONSE_LOGIN, "")
        return jsonString.let { Json.decodeFromString(it) }
    }

    fun setLoginResponse(value: ImageResponse?) {
        createSettings().setLoginResponse(value)
    }

    fun getLoginResponse(): ImageResponse? {
        return createSettings().getLoginResponse()
    }

    fun setEmail(value: String) {
        createSettings()[EMAIL] = value
    }

    fun getEmail(): String {
        return createSettings()[EMAIL, ""]
    }

    fun setPassword(value: String) {
        createSettings()[PASSWORD] = value
    }

    fun getPassword(): String {
        return createSettings()[PASSWORD, ""]
    }

    fun setUserToken(value: String) {
        createSettings()[TOKEN] = value
    }

    fun getUserToken(): String {
        return createSettings()[TOKEN, ""]
    }

    fun setUserImage(value: String) {
        createSettings()[IMAGE_URL] = value
    }

    fun getUserImage(): String {
        return createSettings()[IMAGE_URL, ""]
    }

    fun setIsSaveLogin(value: Boolean) {
        createSettings()[IS_SAVE_LOGIN] = value
    }

    fun getIsSaveLogin(): Boolean {
        return createSettings()[IS_SAVE_LOGIN, false]
    }

    fun setIsAdmin(value: Boolean) {
        createSettings()[IS_ADMIN] = value
    }

    fun getIsAdmin(): Boolean {
        return createSettings()[IS_ADMIN, false]
    }

    fun clear() {
        createSettings().clear()
    }
}