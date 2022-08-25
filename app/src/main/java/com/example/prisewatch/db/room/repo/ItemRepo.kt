package com.example.prisewatch.db.room.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.myapplication.model.Item
import com.example.prisewatch.db.room.dao.ItemDao
import com.example.prisewatch.db.room.db.DataBase
import com.example.prisewatch.db.room.entities.ItemEntity
import java.lang.IllegalStateException

class ItemRepo private constructor(context: Context) {
    private val db: DataBase = Room.databaseBuilder(
        context.applicationContext,
        DataBase::class.java,
        DataBase.NAME
    ).build()

    private val itemDao = db.itemDao()

    companion object {
        private var instance: ItemRepo? = null

        fun initialize(context: Context) {
            if (instance == null) {
                instance = ItemRepo(context)
            }
        }

        fun get(): ItemRepo {
            return instance ?: throw IllegalStateException(" first need initialize repo")
        }
    }

    fun getItems(): List<ItemEntity> {
       return itemDao.getAll()
    }

    fun insertItem(itemEntity: ItemEntity) {
        itemDao.insert(itemEntity)
    }
}