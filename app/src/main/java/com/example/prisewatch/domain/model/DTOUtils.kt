package com.example.prisewatch.domain.model

import com.example.myapplication.model.Item
import com.example.prisewatch.db.room.entities.ItemEntity
import com.example.prisewatch.db.room.entities.ItemWithPrices
import com.example.prisewatch.db.room.entities.PriceEntity

class DTOUtils {
    companion object {
        fun fromEntityToItem(itemWithPrices: ItemWithPrices): Item {
            val item = Item(
                itemWithPrices.entity.id,
                itemWithPrices.entity.title,
                itemWithPrices.entity.urlLink,
                itemWithPrices.entity.urlImg,

                ).apply {
                listPrice = itemWithPrices.listPrice.map { priceEntity ->
                    Price(
                        priceEntity.id,
                        priceEntity.itemId,
                        priceEntity.price,
                        priceEntity.date
                    )
                } as MutableList<Price>
            }

            return item
        }

        fun fromItemToEntity(item: Item): ItemWithPrices {
            val entity = ItemEntity(item.id, item.title, item.urlLink, item.imgUrl, item.host)
            val listPrice = item.listPrice.map { it -> PriceEntity(0, it.id, it.price, it.date) }

            return ItemWithPrices(entity, listPrice)
        }

    }
}