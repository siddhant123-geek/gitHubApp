package com.example.githubapp.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.githubapp.data.GitRepo
import com.example.githubapp.data.ReposDataBase
import com.example.githubapp.di.ActivityContext
import com.example.githubapp.di.ActivityScope
import com.example.githubapp.di.DbName
import com.example.githubapp.ui.home.ReposAdapter
import com.example.githubapp.ui.home.ReposPagingDataAdapter
import com.example.githubapp.utils.NetworkUtils
import com.example.githubapp.utils.ViewModelProviderFactory
import com.example.githubapp.viewModel.ReposViewModel
import com.example.githubapp.viewModel.ReposViewModelWithPagination
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideReposAdapter(): ReposAdapter {
        return ReposAdapter(ArrayList())
    }

    @ActivityScope
    @Provides
    fun provideNetworkUtils(
        @ActivityContext context: Context
    ): NetworkUtils {
        return NetworkUtils(context)
    }

    @DbName
    @Provides
    fun provideDbName(): String = "reposDb"

    @ActivityScope
    @Provides
    fun provideDatabaseService(
        @ActivityContext context: Context,
        @DbName dbName: String
    ): ReposDataBase {
        return Room.databaseBuilder(context, ReposDataBase::class.java, dbName).build()
    }

    @ActivityScope
    @Provides
    fun provideReposViewModel(
        gitRepo: GitRepo,
        dbService: ReposDataBase,
        networkUtils: NetworkUtils
    ): ReposViewModel {
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(
                ReposViewModel::class
            ) {
                ReposViewModel(gitRepo, dbService, networkUtils)
            }
        )[ReposViewModel::class.java]
    }

    @ActivityScope
    @Provides
    fun provideReposViewModelWithPagination(
        gitRepo: GitRepo
    ): ReposViewModelWithPagination {
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(
                ReposViewModelWithPagination::class
            ) {
                ReposViewModelWithPagination(gitRepo)
            }
        )[ReposViewModelWithPagination::class.java]
    }

    @ActivityScope
    @Provides
    fun provideReposPagingAdapter(): ReposPagingDataAdapter {
        return ReposPagingDataAdapter()
    }
}