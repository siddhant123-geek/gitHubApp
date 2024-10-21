package com.example.githubapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.data.GitRepo
import com.example.githubapp.data.ReposDataBase
import com.example.githubapp.data.models.Owner
import com.example.githubapp.data.models.Repo
import com.example.githubapp.data.models.RepoForDb
import com.example.githubapp.utils.NetworkUtils
import com.example.githubapp.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposViewModel(
    private val repo: GitRepo,
    private val dbService: ReposDataBase,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Repo>>>(UiState.Success(ArrayList()))

    val uiState: StateFlow<UiState<List<Repo>>> = _uiState

    fun fetchRepos(name: String) {
        if (networkUtils.isInternetAvailable()) {
            viewModelScope.launch {
                _uiState.value = UiState.Loading
                repo.fetchRepos(name).catch {
                    Log.d(this.javaClass.simpleName, "error - ${it.message}")
                    _uiState.value = UiState.Error(it.message.toString())
                }
                    .collect {
                        _uiState.value = UiState.Success(it)

                        val repoForDbs = it.take(15).map {
                            RepoForDb(
                                name = name,
                                fullName = it.fullName,
                                description = it.description,
                                projectLink = it.projectLink,
                                language = it.language,
                                url = it.owner?.imageUrl,
                                ownerId = it.owner?.id
                            )
                        }
                        withContext(Dispatchers.IO) {
                            dbService.reposDao().deleteRepos()
                            dbService.reposDao().insertRepos(repoForDbs)
                        }
                    }
            }
        } else {
            Log.d(this.javaClass.simpleName, "fetchRepos: coming to no internet case")
            viewModelScope.launch(Dispatchers.IO) {
                _uiState.value = UiState.Loading
                val cachedRepos = dbService.reposDao().getRepos()
                val repos = cachedRepos.map {
                    Repo(
                        name = name,
                        fullName = it.fullName,
                        description = it.description,
                        projectLink = it.projectLink,
                        language = it.language,
                        owner = it.ownerId?.let { it1 -> it.url?.let { it2 -> Owner(it1, it2) } }
                    )
                }
                withContext(Dispatchers.Main) {
                    _uiState.value = UiState.Success(repos)
                }

            }
        }

    }
}