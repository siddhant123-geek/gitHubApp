package com.example.githubapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubapp.data.models.RepoForDb

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<RepoForDb>)

    @Query("SELECT * FROM repos")
    suspend fun getRepos(): List<RepoForDb>

    @Query("DELETE FROM repos")
    suspend fun deleteRepos()
}