package com.pascal.imageai.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(

	@SerialName("amazon")
	val amazon: Amazon? = null,

	@SerialName("openai")
	val openai: Openai? = null,

	@SerialName("replicate")
	val replicate: Replicate? = null,

	@SerialName("deepai")
	val deepai: Deepai? = null,

	@SerialName("stabilityai")
	val stabilityai: Stabilityai? = null
)

@Serializable
data class Error(

	@SerialName("message")
	val message: String? = null,

	@SerialName("type")
	val type: String? = null
)

@Serializable
data class Replicate(

	@SerialName("items")
	val items: List<ItemsItem?>? = null,

	@SerialName("status")
	val status: String? = null
)

@Serializable
data class ItemsItem(

	@SerialName("image_resource_url")
	val imageResourceUrl: String? = null,

	@SerialName("image")
	val image: String? = null
)

@Serializable
data class Amazon(

	@SerialName("items")
	val items: List<ItemsItem?>? = null,

	@SerialName("status")
	val status: String? = null
)

@Serializable
data class Deepai(

	@SerialName("items")
	val items: List<ItemsItem?>? = null,

	@SerialName("status")
	val status: String? = null
)

@Serializable
data class Openai(

	@SerialName("error")
	val error: Error? = null,

	@SerialName("status")
	val status: String? = null,

	@SerialName("provider_status_code")
	val providerStatusCode: Int? = null
)

@Serializable
data class Stabilityai(

	@SerialName("error")
	val error: Error? = null,

	@SerialName("status")
	val status: String? = null,

	@SerialName("provider_status_code")
	val providerStatusCode: Int? = null
)
