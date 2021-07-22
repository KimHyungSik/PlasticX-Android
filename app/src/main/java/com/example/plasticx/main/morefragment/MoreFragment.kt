package com.example.plasticx.main.morefragment

import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plasticx.base.BaseFragment
import com.example.plasticx.databinding.MoreFragmentBinding
import com.example.plasticx.main.morefragment.recyclersetup.InNoticeRecycler
import com.example.plasticx.main.morefragment.recyclersetup.NoticeRecyclerAdapter
import com.example.plasticx.room.RoomRepository
import com.example.plasticx.room.notice.NoticeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoreFragment : BaseFragment<MoreFragmentBinding>(), InNoticeRecycler {

    val TAG = "MoreFragment - 로그"

    override fun getViewBinding(): MoreFragmentBinding = MoreFragmentBinding.inflate(layoutInflater)

    private lateinit var noticeRecyclerAdapter: NoticeRecyclerAdapter
    private lateinit var viewModel: MoreViewModel

    override fun setUpViews() {
        super.setUpViews()

        viewModel = MoreViewModel(RoomRepository(NoticeRepository(activity?.applicationContext!!)))

        recyclerViewSetUp()
        viewModelSetUp()

        CoroutineScope(Dispatchers.Default).launch {
            viewModel.getNoticeList()
        }
    }

    private fun viewModelSetUp(){
        viewModel._noticeList.observe(this, {
            noticeRecyclerAdapter.submitList(it)
            noticeRecyclerAdapter.notifyDataSetChanged()
        })
    }

    private fun recyclerViewSetUp(){
        noticeRecyclerAdapter = NoticeRecyclerAdapter(this)

        binding.moreNoticeRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = noticeRecyclerAdapter
            addItemDecoration(
                DividerItemDecoration(
                    binding.moreNoticeRecyclerView.context,
                    LinearLayoutManager(activity?.applicationContext).orientation
                )
            )
        }

    }

    override fun onClickedItem(position: Int) {
    }
}