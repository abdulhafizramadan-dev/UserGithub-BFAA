package com.ahr.usergithub.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahr.usergithub.data.UserGithubRepository
import com.ahr.usergithub.ui.detail.FollowViewModel
import com.ahr.usergithub.ui.home.ListViewModel
import com.ahr.usergithub.ui.search.SearchViewModel

class UserGithubViewModelFactory(
    private val userGithubRepository: UserGithubRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ListViewModel::class.java) ->
                ListViewModel(userGithubRepository) as T
            modelClass.isAssignableFrom(FollowViewModel::class.java) ->
                FollowViewModel(userGithubRepository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) ->
                SearchViewModel(userGithubRepository) as T
            else -> throw IllegalArgumentException()
        }
    }
}