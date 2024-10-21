package com.example.githubapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.githubapp.data.GitRepo
import com.example.githubapp.data.models.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ReposViewModelWithPagination(private val repo: GitRepo) : ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<Repo>>(PagingData.empty())

    val uiState: StateFlow<PagingData<Repo>> = _uiState

    fun fetchRepos(name: String) {
        viewModelScope.launch {
            repo.fetchReposWithPagination(name).catch {
                Log.d(this.javaClass.simpleName, "error - ${it.message}")
                _uiState.value = PagingData.empty()
            }
                .collect {
                    _uiState.value = it
                }
        }
    }

}