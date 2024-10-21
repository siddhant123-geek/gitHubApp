package com.example.githubapp.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.data.GitRepo
import com.example.githubapp.di.ActivityContext
import com.example.githubapp.di.ActivityScope
import com.example.githubapp.ui.home.ReposAdapter
import com.example.githubapp.utils.ViewModelProviderFactory
import com.example.githubapp.viewModel.ReposViewModel
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
    fun provideReposViewModel(
        gitRepo: GitRepo
    ): ReposViewModel {
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(
                ReposViewModel::class
            ) {
                ReposViewModel(gitRepo)
            }
        )[ReposViewModel::class.java]
    }
}