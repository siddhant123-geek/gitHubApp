package com.example.githubapp.di.modules

import com.example.githubapp.di.ActivityScope
import com.example.githubapp.di.components.ApplicationComponent
import com.example.githubapp.ui.HomeActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {

    fun inject(activity: HomeActivity)
}