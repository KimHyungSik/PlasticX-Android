package com.example.plasticx.dagger.di

import com.example.plasticx.retrofit.repository.RetrofitRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
 class RepositoryModule {
    @Provides fun bindRetrofitRepository() : RetrofitRepository{
        return RetrofitRepository()
    }
}