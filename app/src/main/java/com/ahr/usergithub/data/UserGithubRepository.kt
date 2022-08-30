package com.ahr.usergithub.data

import com.ahr.usergithub.data.network.RemoteDataSource
import com.ahr.usergithub.data.network.Result
import com.ahr.usergithub.data.network.response.ListUserItemResponse
import com.ahr.usergithub.data.network.response.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserGithubRepository(
    private val remoteDataSource: RemoteDataSource
) {

    companion object {
        private var INSTANCE: UserGithubRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): UserGithubRepository =
            INSTANCE ?: synchronized(this) {
                UserGithubRepository(remoteDataSource).apply {
                    INSTANCE = this
                }
            }
    }

    private fun getListUserResponse(token: String): Flow<Response<List<ListUserItemResponse>>> = flow {
        when (val result = remoteDataSource.getListUser(token)) {
            is Result.Success -> {
                emit(Response.Success(result.data))
            }
            is Result.Error -> {
                emit(Response.Error(result.error))
            }
        }
    }

    fun getListUser(token: String): Flow<Response<List<User>>> = flow {
        emit(Response.Loading)
        getListUserResponse(token).collect { listUserResponse ->
            val listUser = arrayListOf<User>()

            when (listUserResponse) {
                is Response.Success -> {
                    listUserResponse.data.forEach {
                        when (val result = remoteDataSource.getUser(token, it.login)) {
                            is Result.Success -> {
                                listUser.add(result.data.toDomain())
                            }
                            is Result.Error -> {
                                emit(Response.Error(result.error))
                                return@collect
                            }
                        }
                    }
                    emit(Response.Success(listUser))
                }
                is Response.Error -> emit(Response.Error(listUserResponse.error))
                is Response.Loading -> {}
            }
        }
    }

    private fun getListUserFollowResponse(token: String, username: String, follow: String): Flow<Response<List<ListUserItemResponse>>> = flow {
        when (val result = remoteDataSource.getListUserFollow(token, username, follow)) {
            is Result.Success -> {
                emit(Response.Success(result.data))
            }
            is Result.Error -> {
                emit(Response.Error(result.error))
            }
        }
    }

    fun getListUserFollow(token: String, username: String, follow: String): Flow<Response<List<User>>> = flow {
        emit(Response.Loading)

        getListUserFollowResponse(token, username, follow).collect { listUserResponse ->
            val listUser = arrayListOf<User>()
            when (listUserResponse) {
                is Response.Success -> {
                    listUserResponse.data.forEach {
                        when (val result = remoteDataSource.getUser(token, it.login)) {
                            is Result.Success -> listUser.add(result.data.toDomain())
                            is Result.Error -> {
                                emit(Response.Error(result.error))
                                return@collect
                            }
                        }
                    }
                    emit(Response.Success(listUser))
                }
                is Response.Error -> emit(Response.Error(listUserResponse.error))
                is Response.Loading -> {}
            }
        }
    }

    private fun searchUserResponse(token: String, query: String): Flow<Response<List<ListUserItemResponse>>> = flow {
        when (val result = remoteDataSource.searchUser(token, query)) {
            is Result.Success -> emit(Response.Success(result.data))
            is Result.Error -> emit(Response.Error(result.error))
        }
    }

    suspend fun searchUser(token: String, query: String): Flow<Response<List<User>>> = flow {
        emit(Response.Loading)
        searchUserResponse(token, query).collect { listUserResponse ->
            when (listUserResponse) {
                is Response.Success -> {
                    val listUser = arrayListOf<User>()
                    listUserResponse.data.forEach {
                        when (val result = remoteDataSource.getUser(token, it.login)) {
                            is Result.Success -> {
                                listUser.add(result.data.toDomain())
                            }
                            is Result.Error -> {
                                emit(Response.Error(result.error))
                                return@collect
                            }
                        }
                    }
                    emit(Response.Success(listUser))
                }
                is Response.Error -> {
                    emit(Response.Error(listUserResponse.error))
                }
                is Response.Loading -> {}
            }
        }
    }
}