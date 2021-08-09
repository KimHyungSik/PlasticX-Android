package com.example.plasticx.main.listfragment

import android.app.ActivityOptions
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plasticx.R
import com.example.plasticx.base.BaseFragment
import com.example.plasticx.databinding.TumblrFragmentBinding
import com.example.plasticx.main.MainActivity
import com.example.plasticx.main.listfragment.RecyclerSetup.InTumblerRecycler
import com.example.plasticx.main.listfragment.RecyclerSetup.TumblerRecyclerAdapter
import com.example.plasticx.main.listfragment.tumblerPage.TumblerDetail
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
        tumblerRecyclerAdapter = TumblerRecyclerAdapter(this)

        binding.tumblerListView.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = tumblerRecyclerAdapter
        }

        (activity as MainActivity).mainComponent.mainComponent().create().inject(this)

        (activity as MainActivity).showLoadingAni()
        viewModel.getTumblerList()
            .subscribe(
                {
                    tumblerRecyclerAdapter.submitList(it)
                    tumblerRecyclerAdapter.notifyDataSetChanged()
                },
                {(activity as MainActivity).dismissLoadingAni()},
                { (activity as MainActivity).dismissLoadingAni() }
            ).isDisposed
    }

    override fun onClickedItem(position: Int) {
        val intent = Intent(activity, TumblerDetail::class.java)
        intent.putExtra("tumblerData", viewModel.tumblerList[position])

        val imageView = activity?.findViewById<View>(R.id.tumbler_list_image)
        val options = ActivityOptions.makeSceneTransitionAnimation(activity, imageView, ViewCompat.getTransitionName(imageView!!))
        startActivity(intent, options.toBundle())
    }
}