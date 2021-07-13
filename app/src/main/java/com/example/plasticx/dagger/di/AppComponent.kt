package com.example.plasticx.dagger.di

import com.example.plasticx.dagger.subcomponent.MainSubComponent
import com.example.plasticx.dagger.subcomponent.SubComponentModule
import com.example.plasticx.login.LoginActivity
import com.example.plasticx.main.MainActivity
import com.example.plasticx.main.listfragment.TumblerFragment
import com.example.plasticx.qr.QrActivity
import com.example.plasticx.registration.RegistrationActivity
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ViewModelFactoryModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    SubComponentModule::class
])
interface AppComponent {
    fun inject(activity: RegistrationActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: QrActivity)
    fun mainComponent(): MainSubComponent.Factory
}