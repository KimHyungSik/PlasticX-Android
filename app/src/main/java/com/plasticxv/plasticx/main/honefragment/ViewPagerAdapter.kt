package com.plasticxv.plasticx.main.honefragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.plasticxv.plasticx.R

class ViewPagerAdapter
    (
    var models: List<Int>,
    var context: Context
) : RecyclerView.Adapter<ViewPagerAdapter.AdapterViewHolder>() {
    override fun getItemCount() = models.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item_card, parent, false)
        return AdapterViewHolder(v)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val item = models[position]
        holder.title.setImageResource(item)
    }

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: ImageView = view.findViewById(R.id.view_pager_image)
    }
}