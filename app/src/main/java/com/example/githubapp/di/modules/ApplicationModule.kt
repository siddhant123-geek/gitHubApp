package com.example.githubapp.di.modules

import android.app.Application
import android.content.Context
import com.example.githubapp.data.models.NetworkService
import com.example.githubapp.di.ApplicationContext
import com.example.githubapp.di.BaseUrl
import com.example.githubapp.utils.AppConstants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return BASE_URL
    }

    @Provides
    @Singleton
    fun provideGsonConvertorFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    @Provides
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonFactory)
            .build()
            .create(NetworkService::class.java)
    }
}