package com.ahr.usergithub.data.network

import com.ahr.usergithub.data.Result
import com.ahr.usergithub.data.network.response.GetUserResponse
import com.ahr.usergithub.data.network.response.ListUserItemResponse
import com.ahr.usergithub.data.network.service.GithubService
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSource(
    private val githubService: GithubService
) {

    companion object {
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(githubService: GithubService): RemoteDataSource =
            INSTANCE ?: synchronized(this) {
                RemoteDataSource(githubService).apply {
                    INSTANCE = this
                }
            }
    }

    suspend fun getListUser(token: String): Result<List<ListUserItemResponse>> =
        withContext(Dispatchers.IO) {
            when (val response = githubService.getListUser(token)) {
                is NetworkResponse.Success -> Result.Success(response.body)
                is NetworkResponse.Error -> Result.Error(response.body?.message)
            }
        }

    suspend fun getListUserFollow(token: String, username: String, follow: String): Result<List<ListUserItemResponse>> =
        withContext(Dispatchers.IO) {
            when (val response = githubService.getUserFollow(token, username, follow)) {
                is NetworkResponse.Success -> Result.Success(response.body)
                is NetworkResponse.Error -> Result.Error(response.body?.message)
            }
        }

    suspend fun getUser(token: String, username: String): Result<GetUserResponse> =
        withContext(Dispatchers.IO) {
            when (val response = githubService.getUser(token, username)) {
                is NetworkResponse.Success -> Result.Success(response.body)
                is NetworkResponse.Error -> Result.Error(response.body?.message)
            }
        }
}