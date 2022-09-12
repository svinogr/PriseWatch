package com.example.prisewatch.db.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.prisewatch.db.room.entities.ItemEntity
import com.example.prisewatch.db.room.entities.ItemWithPrices
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
 /*   @Query("select * from items")
    fun getAll(): Flow<List<ItemWithPrices>>*/

    @Query("select * from items")
    fun getAll():List<ItemWithPrices>

    @Query("select * from items where id = :id")
    fun getItemById(id: Long):ItemWithPrices

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg itemEntity: ItemEntity): Array<Long>
}