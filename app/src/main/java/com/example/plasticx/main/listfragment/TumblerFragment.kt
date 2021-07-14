package com.example.plasticx.main.listfragment

import android.util.Log
import com.example.plasticx.base.BaseFragment
import com.example.plasticx.databinding.TumblrFragmentBinding
import com.example.plasticx.main.MainActivity
import com.example.plasticx.main.listfragment.RecyclerSetup.InTumblerRecycler
import com.example.plasticx.main.listfragment.RecyclerSetup.TumblerRecyclerAdapter
import com.example.plasticx.model.TumblerItem
import javax.inject.Inject
import androidx.recyclerview.widget.LinearLayoutManager

class TumblerFragment : BaseFragment<TumblrFragmentBinding>(), InTumblerRecycler {
    val TAG = "TumblerFragment - 로그"
    
    override fun getViewBinding(): TumblrFragmentBinding  = TumblrFragmentBinding.inflate(layoutInflater)

    private lateinit var tumblerRecyclerAdapter: TumblerRecyclerAdapter

    @Inject
    lateinit var viewModel: TumblerViewModel

    override fun setUpViews() {
        super.setUpViews()
        tumblerRecyclerAdapter = TumblerRecyclerAdapter(this)

        binding.tumblerListView.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = tumblerRecyclerAdapter
        }

        (activity as MainActivity).mainComponent.mainComponent().create().inject(this)

        (activity as MainActivity).showLoadingAni()
        viewModel.getTumblerList()
            .subscribe (
                {
                        tumblerRecyclerAdapter.submitList(it)
                        tumblerRecyclerAdapter.notifyDataSetChanged()
                },
                {},
                {(activity as MainActivity).dismissLoadingAni()}
            ).isDisposed
    }

    override fun onClickedItem(position: Int) {
    }
}