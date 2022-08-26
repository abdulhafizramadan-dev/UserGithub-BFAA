package com.ahr.usergithub.data

import com.ahr.usergithub.data.network.RemoteDataSource
import com.ahr.usergithub.data.network.response.ListUserItemResponse
import com.ahr.usergithub.data.network.response.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserGithubRepository(
    private val remoteDataSource: RemoteDataSource
) {

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
                }
                is Response.Error -> {
                    emit(Response.Error(listUserResponse.error))
                }
                is Response.Loading -> {}
            }

            emit(Response.Success(listUser))
        }
    }
}