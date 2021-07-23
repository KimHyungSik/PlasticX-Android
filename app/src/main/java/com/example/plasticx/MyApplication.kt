package com.example.plasticx

import android.app.Application
import com.example.plasticx.dagger.di.AppComponent
import com.example.plasticx.dagger.di.ApplicationModule
import com.example.plasticx.dagger.di.DaggerAppComponent
import com.example.plasticx.dagger.di.RepositoryModule
import com.kakao.sdk.common.KakaoSdk

class MyApplication: Application() {

    lateinit var appComponent: AppComponent

    companion object{
        lateinit var instance : MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .repositoryModule(RepositoryModule())
            .build()
        instance = this
    }
}