package com.example.myapplication.model

data class Item(var id: Int = 0,
                var title: String = "",
                var price: Double = 0.0,
                var img: String ="") {
    override fun toString(): String {
        return "Item(id=$id, title='$title', price=$price, img='$img')"
    }
}