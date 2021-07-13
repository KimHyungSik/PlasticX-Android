package com.example.plasticx.dagger.subcomponent

import com.example.plasticx.main.MainActivity
import com.example.plasticx.main.listfragment.TumblerFragment
import dagger.Subcomponent

@Subcomponent
interface MainSubComponent {

    @Subcomponent.Factory interface Factory { fun create(): MainSubComponent }

    fun inject(activity: MainActivity)
    fun inject(fragment: TumblerFragment)
}