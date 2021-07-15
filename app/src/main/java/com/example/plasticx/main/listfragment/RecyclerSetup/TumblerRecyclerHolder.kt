package com.example.plasticx.main.listfragment.RecyclerSetup

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plasticx.MyApplication
import com.example.plasticx.R
import com.example.plasticx.model.TumblerItem

class TumblerRecyclerHolder(itemView : View, inRecyclerView: InTumblerRecycler) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    val imageView = itemView.findViewById<ImageView>(R.id.tumbler_list_image)!!
    val tumblerName = itemView.findViewById<TextView>(R.id.tumbler_list_tumbler_name)!!
    val tumblerDay = itemView.findViewById<TextView>(R.id.tumbler_list_tumbler_day)!!
    var inRecyclerView : InTumblerRecycler? = null

    init{
        this.inRecyclerView = inRecyclerView
    }

    override fun onClick(v: View?) {
    }

    fun bindWithView(tumblerItem: TumblerItem) {
        tumblerName.text = tumblerItem.tumblerName
        tumblerDay.text = tumblerItem.tumblerStartDay + '-' + tumblerItem.tumblerEndDay
        Glide.with(MyApplication.instance)
            .load(tumblerItem.imageUrl)
            .placeholder(R.drawable.base_tumbler_icon)
            .into(imageView)
    }

}