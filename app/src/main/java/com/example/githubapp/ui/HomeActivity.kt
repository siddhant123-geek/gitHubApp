package com.example.githubapp.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.GitApplication
import com.example.githubapp.data.models.Repo
import com.example.githubapp.databinding.ActivityHomeScreenBinding
import com.example.githubapp.di.components.ApplicationComponent
import com.example.githubapp.di.components.DaggerActivityComponent
import com.example.githubapp.di.modules.ActivityModule
import com.example.githubapp.utils.UiState
import com.example.githubapp.viewModel.ReposViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding

    @Inject
    lateinit var viewModel: ReposViewModel

    @Inject
    lateinit var adapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()

        binding = ActivityHomeScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setupUi()
        setupObserver()
    }

    fun setupUi() {
        binding.searchButton.setOnClickListener {
            val nameString = binding.searchBar.text.toString()
            Log.d(this.javaClass.name, "setupUi: entered text is $nameString")

            viewModel.fetchRepos(nameString)
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
                viewModel.uiState.collect {
                    Log.d(this.javaClass.simpleName, "setupObserver: uiState value " + it)
                    when(it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            Log.d("###", "setupObserver: uiState value data " + it.data)
                            binding.reposList.visibility = View.VISIBLE
                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Log.d(this@HomeActivity.javaClass.simpleName,
                                "setupObserver: uiState error " + it.e)
                            Toast.makeText(this@HomeActivity,
                                "Error in fetching news - ${it.e}",
                                Toast.LENGTH_LONG).show()
                        }
                        is UiState.Loading -> {
                            Log.d(this@HomeActivity.javaClass.simpleName,
                                "setupObserver: uiState loading ")
                            binding.progressBar.visibility = View.VISIBLE
                            binding.reposList.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun renderList(listOfRepos: List<Repo>) {
        adapter.addListItems(listOfRepos)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as GitApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build().inject(this)
    }
}