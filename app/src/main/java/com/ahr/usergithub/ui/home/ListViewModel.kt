package com.ahr.usergithub.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.usergithub.data.Response
import com.ahr.usergithub.data.User
import com.ahr.usergithub.data.UserGithubRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListViewModel(
    private val githubRepository: UserGithubRepository
) : ViewModel() {

    private val _listUser = MutableLiveData<Response<List<User>>>()
    val listUser: LiveData<Response<List<User>>>
        get() = _listUser

    fun getListUser(token: String) {
        viewModelScope.launch {
            githubRepository.getListUser(token).collectLatest {
                _listUser.postValue(it)
            }
        }
    }
}