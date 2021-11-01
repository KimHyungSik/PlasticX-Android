package com.plasticxv.plasticx.main.morefragment

import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.plasticxv.plasticx.base.BaseFragment
import com.plasticxv.plasticx.databinding.MoreFragmentBinding
import com.plasticxv.plasticx.main.MainActivity
import com.plasticxv.plasticx.main.morefragment.recyclersetup.InNoticeRecycler
import com.plasticxv.plasticx.main.morefragment.recyclersetup.NoticeRecyclerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoreFragment : BaseFragment<MoreFragmentBinding>(), InNoticeRecycler {

    val TAG = "MoreFragment - 로그"

    override fun getViewBinding(): MoreFragmentBinding = MoreFragmentBinding.inflate(layoutInflater)

    private lateinit var noticeRecyclerAdapter: NoticeRecyclerAdapter

    @Inject
    lateinit var viewModel: MoreViewModel

    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).mainComponent.mainComponent().create().inject(this)

        recyclerViewSetUp()
        viewModelSetUp()

        CoroutineScope(Dispatchers.Default).launch {
            viewModel.getNoticeList()
        }
    }

    private fun viewModelSetUp() {
        viewModel._noticeList.observe(this, {
            noticeRecyclerAdapter.submitList(it)
            noticeRecyclerAdapter.notifyDataSetChanged()
            viewModel._loading.postValue(false)
        })
    }

    private fun recyclerViewSetUp() {
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
        CoroutineScope(Dispatchers.Default).launch {
            viewModel._noticeList.value?.get(position)?.let {
                viewModel.deleteNoticeItem(it)
            }
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(activity?.applicationContext, "알림 삭제.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}