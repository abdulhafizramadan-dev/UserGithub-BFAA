package com.ahr.usergithub.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(

	val reposUrl: String = "",

	val followingUrl: String = "",

	val bio: String = "",

	val createdAt: String = "",

	val login: String = "",

	val followersUrl: String = "",

	val publicGists: Int = 0,

	val url: String = "",

	val followers: Int = 0,

	val avatarUrl: String = "",

	val updatedAt: String = "",

	val following: Int = 0,

	val name: String = "",

	val company: String = "",

	val location: String = "",

	val id: Int = 0,

	val publicRepos: Int = 0,

	val email: String = ""
) : Parcelable