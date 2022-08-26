package com.ahr.usergithub.data.network.service

import com.ahr.usergithub.data.network.response.CommonErrorResponse
import com.ahr.usergithub.data.network.response.GetUserResponse
import com.ahr.usergithub.data.network.response.ListUserItemResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GithubService {

    @GET("users")
    suspend fun getListUser(
        @Header("Authorization") token: String
    ): NetworkResponse<List<ListUserItemResponse>, CommonErrorResponse>

    @GET("users/{username}")
    suspend fun getUser(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): NetworkResponse<GetUserResponse, CommonErrorResponse>
}