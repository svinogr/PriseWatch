package com.example.prisewatch.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.prisewatch.model.Price
import java.util.*

@Entity(tableName = "prices")
data class PriceEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "item_id") val itemId: Long,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "date") val date: Date
) {
    fun entityToPrice(): Price {
        return Price(id, price, date)
    }
}