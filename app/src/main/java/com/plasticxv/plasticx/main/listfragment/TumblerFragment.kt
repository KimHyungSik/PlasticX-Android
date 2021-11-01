package com.plasticxv.plasticx.main.listfragment

import android.app.ActivityOptions
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.plasticxv.plasticx.R
import com.plasticxv.plasticx.base.BaseFragment
import com.plasticxv.plasticx.databinding.TumblrFragmentBinding
import com.plasticxv.plasticx.main.MainActivity
import com.plasticxv.plasticx.main.listfragment.RecyclerSetup.InTumblerRecycler
import com.plasticxv.plasticx.main.listfragment.RecyclerSetup.TumblerRecyclerAdapter
import com.plasticxv.plasticx.main.listfragment.tumblerPage.TumblerDetail
import io.reactivex.Observable
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

        Observable.concat(viewModel.getTumblerList(), viewModel.getTumblerHistoryList())
            .subscribe(
                {
                    Log.d(TAG, "setUpViews: $it")
                    viewModel.tumblerList.addAll(it)
                    tumblerRecyclerAdapter.submitList(viewModel.tumblerList)
                    tumblerRecyclerAdapter.notifyDataSetChanged()
                },
                {
                    Log.d(TAG, "setUpViews: $it")
                },
                {
                    (activity as MainActivity).dismissLoadingAni()
                }
            ).isDisposed
    }

    override fun onClickedItem(position: Int) {
        val intent = Intent(activity, TumblerDetail::class.java)
        intent.putExtra("tumblerData", viewModel.tumblerList[position])

        val imageView = activity?.findViewById<View>(R.id.tumbler_list_image)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            activity,
            imageView,
            ViewCompat.getTransitionName(imageView!!)
        )
        startActivity(intent, options.toBundle())
    }
}