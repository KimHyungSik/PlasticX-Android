package com.plasticxv.plasticx.main.morefragment.recyclersetup

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.plasticxv.plasticx.R
import com.plasticxv.plasticx.model.NoticeModel

class NoticeRecyclerHolder(itemView: View, inRecyclerView: InNoticeRecycler): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val TAG = "NoticeRecyclerHolder - 로그"
    
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
        Log.d(TAG, "bindWithView: $noticeModel")
        if(noticeModel.checked){
            Log.d(TAG, "bindWithView: true")
            noticeChecked.setCardBackgroundColor(Color.WHITE)
        }else{
            Log.d(TAG, "bindWithView: false")
            noticeChecked.setCardBackgroundColor(Color.rgb(255, 0, 0))
        }
    }

}