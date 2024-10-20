package com.example.githubapp.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.githubapp.data.GitRepo
import com.example.githubapp.data.models.Repo
import com.example.githubapp.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ReposViewModel(val repo: GitRepo): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Repo>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Repo>>> = _uiState

    fun fetchRepos(name: String) {
        viewModelScope.launch {
            repo.fetchRepos(name)
                .catch {
                    Log.d(this.javaClass.simpleName, "error - ${it.message}")
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}