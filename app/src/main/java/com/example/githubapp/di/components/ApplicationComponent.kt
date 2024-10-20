package com.example.githubapp.di.components

import android.app.Application
import android.content.Context
import com.example.githubapp.data.GitRepo
import com.example.githubapp.data.models.NetworkService
import com.example.githubapp.di.ApplicationContext
import com.example.githubapp.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: Application)

    @ApplicationContext
    fun getContext(): Context

    fun getGitRepo(): GitRepo

    fun getNetworkService(): NetworkService
}