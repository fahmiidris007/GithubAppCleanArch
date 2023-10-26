package com.fahmiproduction.githubappcleanarch.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String
)