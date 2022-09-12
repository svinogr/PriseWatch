package com.example.prisewatch.ui.home.ItemHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.model.Item
import com.example.prisewatch.R
import com.example.prisewatch.ui.home.ItemDetailFragment

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val img: ImageView = itemView.findViewById(R.id.item_card_img)
    val title: TextView = itemView.findViewById(R.id.item_card_title)
    val shop: TextView = itemView.findViewById(R.id.item_card_shop)
    val price: TextView = itemView.findViewById(R.id.item_card_price)
    val imgSign: ImageView = itemView.findViewById(R.id.item_card_sign)

    fun bind(item: Item) {
        Glide.with(itemView.context)
            .load(item.imgUrl)
            .centerCrop()
            .placeholder(R.color.purple_700)
            .into(img)

        title.text = item.title
        shop.text = item.host

        if (item.listPrice.isNotEmpty()) {
            price.text = item.listPrice.last().price.toString()
        }

        itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Navigation.findNavController(itemView).navigate(
                    R.id.action_nav_home_to_itemDetailFragment,
                    bundleOf(ItemDetailFragment.ID_ITEM to item.id)
                )
            }
        })
    }
}
