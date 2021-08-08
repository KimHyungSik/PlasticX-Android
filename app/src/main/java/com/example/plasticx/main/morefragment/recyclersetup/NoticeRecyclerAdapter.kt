package com.example.plasticx.main.morefragment.recyclersetup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plasticx.R
import com.example.plasticx.model.NoticeModel

class NoticeRecyclerAdapter(inNoticeRecycler: InNoticeRecycler):RecyclerView.Adapter<NoticeRecyclerHolder>(){

    var noticeList = ArrayList<NoticeModel>()
    var iRecycler : InNoticeRecycler? = null

    init{
        this.iRecycler = inNoticeRecycler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeRecyclerHolder  =
        NoticeRecyclerHolder(
            LayoutInflater
            .from(parent.context)
            .inflate(R.layout.notice_list_item, parent,false),
            this.iRecycler!!
        )


    override fun onBindViewHolder(holder: NoticeRecyclerHolder, position: Int) {
        holder.bindWithView(this.noticeList[position])
    }

    override fun getItemCount() = noticeList.size

    fun submitList(noticeList: ArrayList<NoticeModel>){
        this.noticeList = noticeList
    }

}