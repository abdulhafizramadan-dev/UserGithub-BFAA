package com.ahr.usergithub.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.usergithub.data.Response
import com.ahr.usergithub.data.User
import com.ahr.usergithub.data.UserGithubRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FollowViewModel(
    private val githubRepository: UserGithubRepository
) : ViewModel() {

    private val _listFollow = MutableLiveData<Response<List<User>>>()
    val listFollow: LiveData<Response<List<User>>>
        get() = _listFollow

    private val _firstLoad = MutableLiveData(true)
    val firstLoad: LiveData<Boolean> get() = _firstLoad

    fun getListUserFollow(token: String, username: String, follow: String) {
        viewModelScope.launch {
            githubRepository.getListUserFollow(token, username ,follow).collectLatest {
                _firstLoad.postValue(false)
                _listFollow.postValue(it)
            }
        }
    }

}