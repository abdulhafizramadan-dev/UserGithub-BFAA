package com.ahr.usergithub.data

import android.content.Context
import com.ahr.usergithub.R

object UserData {

    private fun getUserAvatar(): Array<Int> = arrayOf(
        R.drawable.user1,
        R.drawable.user2,
        R.drawable.user3,
        R.drawable.user4,
        R.drawable.user5,
        R.drawable.user6,
        R.drawable.user7,
        R.drawable.user8,
        R.drawable.user9,
        R.drawable.user10,
    )

    private fun getUserUsername(context: Context): Array<String> =
        context.resources.getStringArray(R.array.username)

    private fun getUserName(context: Context): Array<String> =
        context.resources.getStringArray(R.array.name)

    private fun getUserLocation(context: Context): Array<String> =
        context.resources.getStringArray(R.array.location)

    private fun getUserRepository(context: Context): Array<String> =
        context.resources.getStringArray(R.array.repository)

    private fun getUserCompany(context: Context): Array<String> =
        context.resources.getStringArray(R.array.company)

    private fun getUserFollowers(context: Context): Array<String> =
        context.resources.getStringArray(R.array.followers)

    private fun getUserFollowing(context: Context): Array<String> =
        context.resources.getStringArray(R.array.following)

    fun getUserData(context: Context): List<User> {
        val userAvatars = getUserAvatar()
        val userUsernames = getUserUsername(context)
        val userNames = getUserName(context)
        val userLocations = getUserLocation(context)
        val userRepositories = getUserRepository(context)
        val userCompany = getUserCompany(context)
        val userFollowers = getUserFollowers(context)
        val userFollowing = getUserFollowing(context)

        val userData = userAvatars.mapIndexed { index, _ ->
            User(
                avatar = userAvatars[index],
                name = userNames[index],
                username = userUsernames[index],
                location = userLocations[index],
                follower = userFollowers[index],
                following = userFollowing[index],
                company = userCompany[index],
                repository = userRepositories[index]
            )
        }

        return userData
    }
}