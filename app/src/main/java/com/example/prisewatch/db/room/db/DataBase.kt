package com.example.prisewatch.db.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.prisewatch.db.room.dao.ItemDao
import com.example.prisewatch.db.room.dao.PriceDao
import com.example.prisewatch.db.room.entities.ItemEntity
import com.example.prisewatch.db.room.entities.PriceEntity


@Database(entities = [ItemEntity::class, PriceEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class DataBase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun priceDao(): PriceDao

    companion object {
        const val NAME = "initial"
    }
}