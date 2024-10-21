package com.example.githubapp.di.components

import com.example.githubapp.di.ActivityScope
import com.example.githubapp.di.modules.ActivityModule
import com.example.githubapp.ui.home.HomeActivity
import com.example.githubapp.ui.home.HomeActivityWithPagination
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {

    fun inject1(activity: HomeActivity)

    fun inject2(activity: HomeActivityWithPagination)
}