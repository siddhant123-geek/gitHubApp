package com.example.githubapp.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubapp.data.models.NetworkService
import com.example.githubapp.data.models.Repo
import com.example.githubapp.utils.AppConstants.INITIAL_PAGE
import com.example.githubapp.utils.AppConstants.PER_PAGE

class ReposPagingSource(private val networkService: NetworkService,
                        private val searchedName: String) :
    PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: INITIAL_PAGE

            val response = networkService.getReposWithPagination(
                name = searchedName,
                page = page,
                perPage = PER_PAGE
            )
            Log.d(this.javaClass.simpleName, "load: responseSize " + response.items.size)
            LoadResult.Page(
                data = response.items,
                prevKey = if (page == INITIAL_PAGE) null else page.minus(1),
                nextKey = if (response.items.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}