package com.example.plasticx.main.listfragment

import com.example.plasticx.base.BaseFragment
import com.example.plasticx.dagger.di.AppComponent
import com.example.plasticx.databinding.TumblrFragmentBinding
import com.example.plasticx.main.MainActivity
import com.example.plasticx.main.listfragment.RecyclerSetup.InTumblerRecycler
import com.example.plasticx.main.listfragment.RecyclerSetup.TumblerRecyclerAdapter
import javax.inject.Inject

class TumblerFragment : BaseFragment<TumblrFragmentBinding>(), InTumblerRecycler {
    override fun getViewBinding(): TumblrFragmentBinding  = TumblrFragmentBinding.inflate(layoutInflater)

    private lateinit var tumblerRecyclerAdapter: TumblerRecyclerAdapter

    @Inject
    lateinit var viewModel: TumblerViewModel

    override fun setUpViews() {
        super.setUpViews()
        tumblerRecyclerAdapter = TumblerRecyclerAdapter(this)
        (activity as MainActivity).mainComponent.inject(this)
        viewModel.getTumblerList()
    }

    override fun onClickedItem(position: Int) {
    }
}