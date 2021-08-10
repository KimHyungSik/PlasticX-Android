package com.example.plasticx.main.listfragment.RecyclerSetup

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plasticx.MyApplication
import com.example.plasticx.R
import com.example.plasticx.model.TumblerItem

class TumblerRecyclerHolder(itemView : View, inRecyclerView: InTumblerRecycler) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    private val imageView = itemView.findViewById<ImageView>(R.id.tumbler_list_image)!!
    private val tumblerName = itemView.findViewById<TextView>(R.id.tumbler_list_tumbler_name)!!
    private val tumblerDay = itemView.findViewById<TextView>(R.id.tumbler_list_tumbler_day)!!
    private val tumblerBorrowStatus = itemView.findViewById<TextView>(R.id.tumbler_list_borrow_status)!!
    private val tumblerListViewItem: CardView = itemView.findViewById<CardView>(R.id.tumbler_list_view_item)
    private val tumblerBorrowStatusViewItem: CardView  = itemView.findViewById<CardView>(R.id.tumbler_list_borrow_status_layout)
    private var inRecyclerView : InTumblerRecycler? = null

    init{
        this.inRecyclerView = inRecyclerView
        this.tumblerListViewItem.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        this.inRecyclerView?.onClickedItem(adapterPosition)
    }

    @SuppressLint("SetTextI18n")
    fun bindWithView(tumblerItem: TumblerItem) {
        tumblerName.text = if(tumblerItem.tumblerName == "")  "텀블러" else tumblerItem.tumblerName
        tumblerDay.text = tumblerItem.tumblerStartDay + " ~ " + tumblerItem.tumblerEndDay
        Glide.with(MyApplication.instance)
            .load(tumblerItem.imageUrl)
            .placeholder(R.drawable.base_tumbler_icon)
            .into(imageView)
        if(tumblerItem.status) tumblerBorrowStatus.text = "대여중"
        if(!tumblerItem.status){
            tumblerBorrowStatus.text = "반납완료"
            tumblerBorrowStatusViewItem.setCardBackgroundColor(Color.rgb(70, 58, 62))
            tumblerListViewItem.setCardBackgroundColor(Color.rgb(204,204,204))
        }
    }

}