package com.pascal.imageai.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseAction(

	@SerialName("code")
	val code: Int? = null,

	@SerialName("success")
	val success: Boolean? = null,

	@SerialName("message")
	val message: String? = null
)
