package com.example.githubapp.data

import com.example.githubapp.data.models.NetworkService
import com.example.githubapp.data.models.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitRepo @Inject constructor(private val networkService: NetworkService) {

    suspend fun fetchRepos(name: String): Flow<List<Repo>> {
        return flow {
            emit(networkService.getRepos(name))
        }
            .map {
                it.items
            }
    }
}