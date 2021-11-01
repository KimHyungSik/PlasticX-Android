package com.plasticxv.plasticx.main.listfragment.RecyclerSetup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.plasticxv.plasticx.R
import com.plasticxv.plasticx.model.TumblerItem

class TumblerRecyclerAdapter(inTumblerRecycler: InTumblerRecycler): RecyclerView.Adapter<TumblerRecyclerHolder>() {

    var tumblerlist = ArrayList<TumblerItem>()
    var iRecycler : InTumblerRecycler? = null

    init{
        this.iRecycler = inTumblerRecycler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TumblerRecyclerHolder {
        return TumblerRecyclerHolder(
            LayoutInflater
            .from(parent.context)
            .inflate(R.layout.tumbler_list_item, parent,false),
            this.iRecycler!!
        )
    }

    override fun onBindViewHolder(holder: TumblerRecyclerHolder, position: Int) {
        val dataItem: TumblerItem = this.tumblerlist[position]
        holder.bindWithView(dataItem)
    }

    override fun getItemCount() = tumblerlist.size

    fun submitList(tumblerList: ArrayList<TumblerItem>){
        this.tumblerlist = tumblerList
    }
}