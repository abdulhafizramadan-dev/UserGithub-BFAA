package com.ahr.usergithub.data.network.response

import com.google.gson.annotations.SerializedName

data class ListUserItemResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("login")
	val login: String
)