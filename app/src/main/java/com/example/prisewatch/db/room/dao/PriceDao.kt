package com.example.prisewatch.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.prisewatch.db.room.entities.PriceEntity

@Dao
interface PriceDao {
    @Insert
    fun insert(price: List<PriceEntity>): Array<Long>
}