package com.example.prisewatch.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.prisewatch.db.room.entities.ItemEntity

@Dao
interface ItemDao {
    @Query("select * from items")
    fun getAll(): List<ItemEntity>

    @Insert
    fun insert(vararg itemEntity: ItemEntity)
}