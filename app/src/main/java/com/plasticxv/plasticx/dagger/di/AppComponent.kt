package com.plasticxv.plasticx.dagger.di

import com.plasticxv.plasticx.dagger.subcomponent.MainSubComponent
import com.plasticxv.plasticx.dagger.subcomponent.SubComponentModule
import com.plasticxv.plasticx.login.LoginActivity
import com.plasticxv.plasticx.qr.QrActivity
import com.plasticxv.plasticx.registration.RegistrationActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ViewModelFactoryModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    SubComponentModule::class,
    ApplicationModule::class
])
interface AppComponent {
    fun inject(activity: RegistrationActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: QrActivity)
    // 메인 액티비티의 fragment 의존성 주입
    fun mainComponent(): MainSubComponent.Factory
}