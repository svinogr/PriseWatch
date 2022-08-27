package com.example.prisewatch.db.room.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.model.Item
import com.example.prisewatch.model.Price

data class ItemWithPrices(
    @Embedded val entity: ItemEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "item_id"
    )
    val listPrice: MutableList<PriceEntity>
) {
    fun entityToItem(): Item {
        return Item(entity.id, entity.title, entity.urlLink).also {
            it.listPrice = listPrice.map { it.entityToPrice() } as MutableList<Price>
        }
    }
}
