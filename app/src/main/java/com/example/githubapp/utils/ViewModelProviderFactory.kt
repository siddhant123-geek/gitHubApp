package com.example.githubapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

class ViewModelProviderFactory<T: ViewModel>(
    private val viewModel: KClass<T>,
    private val creator: () -> T
):  ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(viewModel.java)) return creator.invoke() as T
        throw IllegalArgumentException("Unknown class name")
    }

}