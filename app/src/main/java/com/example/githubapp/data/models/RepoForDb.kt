package com.example.githubapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoForDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "repoId")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String? = "",
    @ColumnInfo(name = "fullName")
    val fullName: String? = "",
    @ColumnInfo(name = "description")
    val description: String? = "",
    @ColumnInfo(name = "projectLink")
    val projectLink: String? = "",
    @ColumnInfo(name = "language")
    val language: String? = "",
    @ColumnInfo(name = "imageUrl")
    val url: String? = null,
    @ColumnInfo(name = "id")
    val ownerId: Long? = 1L
)
