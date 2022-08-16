package com.ahr.usergithub.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
	val follower: String,
	val following: String,
	val name: String,
	val company: String,
	val location: String,
	val avatar: Int,
	val repository: String,
	val username: String
) : Parcelable
