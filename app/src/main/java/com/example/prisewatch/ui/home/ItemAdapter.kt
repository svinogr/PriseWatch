package com.example.prisewatch.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Item
import com.example.prisewatch.R
import com.example.prisewatch.ui.home.ItemHolder.ItemHolder

class ItemAdapter(var list: List<Item>) : RecyclerView.Adapter<ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ItemHolder(inflate)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}