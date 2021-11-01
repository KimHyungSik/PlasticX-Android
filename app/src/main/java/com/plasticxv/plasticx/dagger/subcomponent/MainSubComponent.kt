package com.plasticxv.plasticx.dagger.subcomponent

import com.plasticxv.plasticx.dagger.annotation.MainActivityScope
import com.plasticxv.plasticx.main.MainActivity
import com.plasticxv.plasticx.main.listfragment.TumblerFragment
import com.plasticxv.plasticx.main.morefragment.MoreFragment
import dagger.Subcomponent

@MainActivityScope
@Subcomponent
interface MainSubComponent {

    @Subcomponent.Factory interface Factory { fun create(): MainSubComponent }

    fun inject(activity: MainActivity)
    fun inject(fragment: TumblerFragment)
    fun inject(fragment: MoreFragment)
}