package com.example.githubapp.data

import com.example.githubapp.data.models.Repo
import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val inCompleteRes: Boolean,
    @SerializedName("items")
    val items: List<Repo>
)
