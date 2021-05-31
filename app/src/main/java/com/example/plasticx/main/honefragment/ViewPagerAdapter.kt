package com.example.plasticx.main.honefragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plasticx.R

class ViewPagerAdapter
    (
    var models: List<String>,
    var context: Context
) : RecyclerView.Adapter<ViewPagerAdapter.AdapterViewHolder>() {
    override fun getItemCount(): Int {
        return models.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item_card, parent, false)
        return AdapterViewHolder(v)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val item = models[position]
        holder.title.text = item
    }

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.viewpager_item_title)
    }
}