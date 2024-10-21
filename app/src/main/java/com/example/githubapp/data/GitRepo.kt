package com.example.githubapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubapp.data.models.NetworkService
import com.example.githubapp.data.models.Repo
import com.example.githubapp.utils.AppConstants
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

    fun fetchReposWithPagination(name: String): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(
                pageSize = AppConstants.PER_PAGE
            ),
            pagingSourceFactory = {
                ReposPagingSource(networkService, name)
            }
        ).flow
    }
}