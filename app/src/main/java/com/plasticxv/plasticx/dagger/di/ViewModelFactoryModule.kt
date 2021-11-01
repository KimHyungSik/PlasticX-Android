package com.plasticxv.plasticx.dagger.di

import androidx.lifecycle.ViewModelProvider
import com.plasticxv.plasticx.dagger.viewmodelFactory.RetrofitFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: RetrofitFactory) : ViewModelProvider.Factory
}