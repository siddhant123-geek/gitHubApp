package com.example.githubapp

import android.app.Application
import com.example.githubapp.di.components.ApplicationComponent
import com.example.githubapp.di.components.DaggerApplicationComponent
import com.example.githubapp.di.modules.ApplicationModule

class GitApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        injectDependencies()
        super.onCreate()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}