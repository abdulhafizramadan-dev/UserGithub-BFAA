package com.ahr.usergithub.data.network.service

import com.ahr.usergithub.data.network.response.CommonErrorResponse
import com.ahr.usergithub.data.network.response.GetUserResponse
import com.ahr.usergithub.data.network.response.ListUserItemResponse
import com.ahr.usergithub.data.network.response.SearchUserResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    suspend fun getListUser(
        @Header("Authorization") token: String
    ): NetworkResponse<List<ListUserItemResponse>, CommonErrorResponse>

    @GET("users/{username}/{follow}")
    suspend fun getUserFollow(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Path("follow") follow: String
    ): NetworkResponse<List<ListUserItemResponse>, CommonErrorResponse>

    @GET("users/{username}")
    suspend fun getUser(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): NetworkResponse<GetUserResponse, CommonErrorResponse>

    @GET("search/users")
    suspend fun searchUser(
        @Header("Authorization") token: String,
        @Query("q") query: String
    ): NetworkResponse<SearchUserResponse, CommonErrorResponse>
}