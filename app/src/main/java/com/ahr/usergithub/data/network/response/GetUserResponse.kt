package com.ahr.usergithub.data.network.response

import com.ahr.usergithub.data.User
import com.google.gson.annotations.SerializedName

data class GetUserResponse(

	@field:SerializedName("repos_url")
	val reposUrl: String? = null,

	@field:SerializedName("following_url")
	val followingUrl: String? = null,

	@field:SerializedName("bio")
	val bio: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("followers_url")
	val followersUrl: String? = null,

	@field:SerializedName("public_gists")
	val publicGists: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("followers")
	val followers: Int? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("following")
	val following: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("company")
	val company: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("public_repos")
	val publicRepos: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)

fun GetUserResponse.toDomain(): User =
	User(
		reposUrl = reposUrl ?: "-",
		followingUrl = followingUrl ?: "-",
		createdAt = createdAt ?: "-",
		bio = bio ?: "-",
		login = login ?: "-",
		followersUrl = followersUrl ?: "-",
		publicGists = publicGists ?: 0,
		url = url ?: "-",
		followers = followers ?: 0,
		avatarUrl = avatarUrl ?: "-",
		updatedAt = updatedAt ?: "-",
		following = following ?: 0,
		name = name ?: "-",
		company = company ?: "-",
		location = location ?: "-",
		id = id ?: 0,
		publicRepos = publicRepos ?: 0,
		email = email ?: "-"
	)