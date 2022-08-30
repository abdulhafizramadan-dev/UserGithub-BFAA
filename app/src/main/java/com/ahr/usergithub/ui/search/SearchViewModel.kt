package com.ahr.usergithub.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.usergithub.data.Response
import com.ahr.usergithub.data.User
import com.ahr.usergithub.data.UserGithubRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchViewModel(
    private val githubRepository: UserGithubRepository
) : ViewModel() {

    private val _listUser = MutableLiveData<Response<List<User>>>()
    val listUser: LiveData<Response<List<User>>>
        get() = _listUser

    private val _firstLoad = MutableLiveData(true)
    val firstLoad: LiveData<Boolean> get() = _firstLoad

    fun searchUser(token: String, query: String) {
        viewModelScope.launch {
            githubRepository.searchUser(token, query).collectLatest {
                _firstLoad.postValue(false)
                _listUser.postValue(it)
            }
        }
    }

}