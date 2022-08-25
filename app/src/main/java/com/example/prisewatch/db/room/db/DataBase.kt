package com.example.prisewatch.db.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prisewatch.db.room.dao.ItemDao
import com.example.prisewatch.db.room.entities.ItemEntity


@Database(entities = [ItemEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        const val NAME = "item_database"
    }
}