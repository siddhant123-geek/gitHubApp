package com.example.githubapp.data.models

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("id")
    val id: Long,
    @SerializedName("avatar_url")
    val imageUrl: String
)
