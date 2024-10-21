package com.example.githubapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubapp.data.models.RepoForDb

@Database(entities = [RepoForDb::class], version = 1)
abstract class ReposDataBase: RoomDatabase() {

    abstract fun reposDao(): RepoDao
}