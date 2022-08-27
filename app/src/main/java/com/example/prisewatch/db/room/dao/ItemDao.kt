package com.example.prisewatch.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.prisewatch.db.room.entities.ItemEntity
import com.example.prisewatch.db.room.entities.ItemWithPrices

@Dao
interface ItemDao {
    @Query("select * from items")
    fun getAll(): List<ItemWithPrices>

    @Query("select * from items where id = :id")
    fun getItemById(id: Long): ItemWithPrices

    @Insert
    fun insert(vararg itemEntity: ItemEntity)
}