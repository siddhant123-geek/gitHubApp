package com.example.githubapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.GitApplication
import com.example.githubapp.data.models.Repo
import com.example.githubapp.data.models.RepoDetail
import com.example.githubapp.databinding.ActivityHomeScreenBinding
import com.example.githubapp.databinding.ActivityHomeScreenWithPaginationBinding
import com.example.githubapp.di.components.DaggerActivityComponent
import com.example.githubapp.di.modules.ActivityModule
import com.example.githubapp.ui.detail.RepoDetailActivity
import com.example.githubapp.utils.NetworkUtils
import com.example.githubapp.utils.UiState
import com.example.githubapp.viewModel.ReposViewModel
import com.example.githubapp.viewModel.ReposViewModelWithPagination
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeActivityWithPagination : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenWithPaginationBinding

    @Inject
    lateinit var viewModel: ReposViewModelWithPagination

    @Inject
    lateinit var adapter: ReposPagingDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()

        binding = ActivityHomeScreenWithPaginationBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setupUi()
        setupObserver()
        setupClickListenerForEachRepo()
    }

    private fun setupClickListenerForEachRepo() {
        adapter.openRepoDetailsCallback = {
            // launch new screen
            val intent = Intent(this, RepoDetailActivity::class.java)
            val repoDetail = RepoDetail(
                it.name,
                it.description,
                it.projectLink,
                it.language,
                it.owner?.imageUrl
            )
            Log.d(this.javaClass.simpleName,
                "setupClickListenerForEachRepo: repoDetail put in the extras " +
                        repoDetail.name)
            intent.putExtra("repoDetail", repoDetail)
            startActivity(intent)
        }
    }

    private fun setupUi() {
        binding.searchButton.setOnClickListener {
            val nameString = binding.searchBar.text.toString()
            Log.d(this.javaClass.name, "setupUi: entered text is $nameString")

            viewModel.fetchRepos(nameString)
        }

        binding.getWithoutPagination.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        val recyclerView = binding.reposList
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as GitApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build().inject2(this)
    }
}