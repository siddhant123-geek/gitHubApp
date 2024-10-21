package com.example.githubapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.data.models.Repo
import com.example.githubapp.databinding.RepoItemBinding

class ReposAdapter(private val reposList: ArrayList<Repo>):
    RecyclerView.Adapter<ReposAdapter.DataViewHolder>() {
        var openDetailScreenCallback: ((Repo) -> Unit)? = null
    inner class DataViewHolder(private val binding: RepoItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repo) {
            binding.repoName.text = repo.name
            binding.repoFullName.text = repo.fullName
            binding.description.text = repo.description

            binding.root.setOnClickListener {
                openDetailScreenCallback?.invoke(repo)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            RepoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return reposList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(reposList[position])
    }

    fun addListItems(list: List<Repo>) {
        reposList.clear()
        reposList.addAll(list)
    }
}