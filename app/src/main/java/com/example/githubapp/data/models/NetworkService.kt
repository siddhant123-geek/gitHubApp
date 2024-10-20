package com.example.githubapp.data.models

import com.example.githubapp.data.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

interface NetworkService {

    @GET("search/repositories")
    suspend fun getRepos(@Query("q") name: String): RepoResponse
}