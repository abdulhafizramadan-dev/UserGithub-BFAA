package com.ahr.usergithub.data.network.response

import com.google.gson.annotations.SerializedName

data class CommonErrorResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("documentation_url")
	val documentationUrl: String? = null
)
