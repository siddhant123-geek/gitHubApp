package com.example.githubapp.data.models

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("full_name")
    val fullName: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("html_url")
    val projectLink: String? = "",
    @SerializedName("language")
    val language: String? = "",
    @SerializedName("owner")
    val owner: Owner?
)
