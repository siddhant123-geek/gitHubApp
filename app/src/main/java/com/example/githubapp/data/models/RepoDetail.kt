package com.example.githubapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepoDetail(
    val name: String? = "",
    val description: String? ="",
    val projectLink: String? ="",
    val language: String? = "",
    val imageUrl: String? = ""
): Parcelable
