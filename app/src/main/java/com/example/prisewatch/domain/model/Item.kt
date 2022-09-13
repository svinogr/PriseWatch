package com.example.myapplication.model

import com.example.prisewatch.domain.model.Price

data class Item(
    var id: Long = 0,
    var title: String = "",
    var urlLink: String = "",
    var imgLink: String = "",
    var host: String = ""
) {
    var priceList: MutableList<Price> = mutableListOf()

    override fun toString(): String {
        return "Item(id=$id ,\n title='$title', \n $host,  \n price=${priceList},\n img='$imgLink')"
    }

    fun lastPrice(): Price {
        return priceList.last()
    }

    fun fullLinImg(): String {
        return host + imgLink
    }
}

