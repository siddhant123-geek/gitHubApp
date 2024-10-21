package com.example.githubapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.data.models.Repo
import com.example.githubapp.databinding.RepoItemBinding

class ReposPagingDataAdapter:
    PagingDataAdapter<Repo, ReposPagingDataAdapter.DataViewHolder>(RepoDiffCallback()) {

        var openRepoDetailsCallback: ((Repo) -> Unit)? = null
    inner class DataViewHolder(private val repoItemBinding: RepoItemBinding) :
        RecyclerView.ViewHolder(repoItemBinding.root) {

        fun bind(repo: Repo) {
            repoItemBinding.repoName.text = repo.name
            repoItemBinding.repoFullName.text = repo.fullName
            repoItemBinding.description.text = repo.description

            repoItemBinding.root.setOnClickListener {
                openRepoDetailsCallback?.invoke(repo)
            }
        }
    }

    class RepoDiffCallback : DiffUtil.ItemCallback<Repo>() {
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: Repo,
            newItem: Repo
        ) = oldItem == newItem

        override fun getChangePayload(oldItem: Repo, newItem: Repo): Any? {
            if (oldItem != newItem) {
                return newItem
            }
            return super.getChangePayload(oldItem, newItem)
        }
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item: Repo? = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    override fun onBindViewHolder(
        holder: DataViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNullOrEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val newItem = payloads[0] as Repo
            holder.bind(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RepoItemBinding.inflate(inflater)
        return DataViewHolder(binding)
    }
}