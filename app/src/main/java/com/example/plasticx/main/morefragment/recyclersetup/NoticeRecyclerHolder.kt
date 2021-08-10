package com.example.plasticx.main.morefragment.recyclersetup

import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plasticx.MyApplication
import com.example.plasticx.R
import com.example.plasticx.model.NoticeModel
import com.example.plasticx.model.TumblerItem
import okhttp3.internal.addHeaderLenient

class NoticeRecyclerHolder(itemView: View, inRecyclerView: InNoticeRecycler): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private val noticeListView = itemView.findViewById<LinearLayout>(R.id.Notice_item_view)!!
    private val noticeTitle = itemView.findViewById<TextView>(R.id.notice_item_title)!!
    private val noticeContent = itemView.findViewById<TextView>(R.id.notice_item_content)!!
    private var inRecyclerView : InNoticeRecycler? = null
    private val noticeChecked = itemView.findViewById<CardView>(R.id.notice_checked)

    init{
        this.inRecyclerView = inRecyclerView
        this.noticeListView .setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        this.inRecyclerView?.onClickedItem(adapterPosition)
    }

    fun bindWithView(noticeModel: NoticeModel) {
        noticeTitle.text = noticeModel.title
        noticeContent.text = noticeModel.content
        if(noticeModel.checked){
            noticeChecked.setCardBackgroundColor(Color.WHITE)
        }else{
            noticeChecked.setCardBackgroundColor(Color.rgb(255, 0, 0))
        }
    }

}