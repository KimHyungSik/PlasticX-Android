package com.example.plasticx.main.listfragment

import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plasticx.base.BaseFragment
import com.example.plasticx.databinding.TumblrFragmentBinding
import com.example.plasticx.main.MainActivity
import com.example.plasticx.main.listfragment.RecyclerSetup.InTumblerRecycler
import com.example.plasticx.main.listfragment.RecyclerSetup.TumblerRecyclerAdapter
import javax.inject.Inject


class TumblerFragment : BaseFragment<TumblrFragmentBinding>(), InTumblerRecycler {
    val TAG = "TumblerFragment - 로그"

    override fun getViewBinding(): TumblrFragmentBinding = TumblrFragmentBinding.inflate(
        layoutInflater
    )

    private lateinit var tumblerRecyclerAdapter: TumblerRecyclerAdapter

    @Inject
    lateinit var viewModel: TumblerViewModel

    override fun setUpViews() {
        super.setUpViews()
        Log.d(TAG, "setUpViews: ")
        tumblerRecyclerAdapter = TumblerRecyclerAdapter(this)

        binding.tumblerListView.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = tumblerRecyclerAdapter
            addItemDecoration(
                DividerItemDecoration(
                    binding.tumblerListView.context,
                    LinearLayoutManager(activity?.applicationContext).orientation
                )
            )
        }

        (activity as MainActivity).mainComponent.mainComponent().create().inject(this)

        (activity as MainActivity).showLoadingAni()
        viewModel.getTumblerList()
            .subscribe(
                {
                    tumblerRecyclerAdapter.submitList(it)
                    tumblerRecyclerAdapter.notifyDataSetChanged()
                },
                {},
                { (activity as MainActivity).dismissLoadingAni() }
            ).isDisposed
    }

    override fun onClickedItem(position: Int) {
        Log.d(TAG, "onClickedItem: ${viewModel.tumblerList[position]}")
    }
}