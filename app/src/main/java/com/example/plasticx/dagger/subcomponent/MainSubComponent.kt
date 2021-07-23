package com.example.plasticx.dagger.subcomponent

import com.example.plasticx.dagger.annotation.MainActivityScope
import com.example.plasticx.main.MainActivity
import com.example.plasticx.main.listfragment.TumblerFragment
import com.example.plasticx.main.morefragment.MoreFragment
import dagger.Subcomponent

@MainActivityScope
@Subcomponent
interface MainSubComponent {

    @Subcomponent.Factory interface Factory { fun create(): MainSubComponent }

    fun inject(activity: MainActivity)
    fun inject(fragment: TumblerFragment)
    fun inject(fragment: MoreFragment)
}