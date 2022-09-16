package com.example.prisewatch.domain.model

import java.util.*

data class Price(
    val id: Long,
    val itemId: Long,
    val price: Double,
    val date: Long


) {
    override fun toString(): String {
        return "Price(id=$id, itemId=$itemId, price=$price, date=$date)"
    }
}
