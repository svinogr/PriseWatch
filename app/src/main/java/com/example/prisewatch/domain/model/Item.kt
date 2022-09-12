package com.example.myapplication.model

import com.example.prisewatch.domain.model.Price
import java.net.URL

data class Item(
    var id: Long = 0,
    var title: String = "",
    var urlLink: String = "",
    var imgUrl: String = "",
    var host: String = ""
) {
    var listPrice: MutableList<Price> = mutableListOf()

    override fun toString(): String {
        return "Item(id=$id ,\n title='$title', \n $host,  \n price=${listPrice},\n img='$imgUrl')"
    }
}

