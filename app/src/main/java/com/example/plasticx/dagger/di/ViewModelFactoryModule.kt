package com.example.plasticx.dagger.di

import androidx.lifecycle.ViewModelProvider
import com.example.plasticx.dagger.viewmodelFactory.RetrofitFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: RetrofitFactory) : ViewModelProvider.Factory
}