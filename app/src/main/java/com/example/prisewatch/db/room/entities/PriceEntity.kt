package com.example.prisewatch.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.prisewatch.domain.model.Price
import java.util.*

@Entity(tableName = "prices")
data class PriceEntity (
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "item_id") var itemId: Long,
    @ColumnInfo(name = "price") var price: Double,
    @ColumnInfo(name = "date") var date: Long
) {
    fun entityToPrice(): Price {
        return Price(id, itemId, price, date)
    }
}