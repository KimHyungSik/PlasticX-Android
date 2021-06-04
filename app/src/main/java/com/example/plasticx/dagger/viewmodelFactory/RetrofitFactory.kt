package com.example.plasticx.dagger.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plasticx.registration.RegistrationViewModel
import com.example.plasticx.retrofit.repository.RetrofitRepository

class RetrofitFactory(private val retrofitRepository: RetrofitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            RegistrationViewModel(retrofitRepository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}