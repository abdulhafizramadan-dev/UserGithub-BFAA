package com.ahr.usergithub.data.network.service

import com.ahr.usergithub.BuildConfig
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubConfig {
    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor()
            .setLevel(if (BuildConfig.DEBUG) BODY else NONE)
    }
    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    fun getService(): GithubService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(GithubService::class.java)
}