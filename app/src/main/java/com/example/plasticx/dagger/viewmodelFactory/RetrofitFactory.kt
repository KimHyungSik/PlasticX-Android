package com.example.plasticx.dagger.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plasticx.registration.RegistrationViewModel
import com.example.plasticx.retrofit.repository.RetrofitRepository
import javax.inject.Inject
import javax.inject.Provider

class RetrofitFactory @Inject constructor(val viewModelMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelMap[modelClass]?.get() as T
    }

}