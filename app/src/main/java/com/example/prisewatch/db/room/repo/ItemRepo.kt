package com.example.prisewatch.db.room.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.Transaction
import com.example.myapplication.model.Item
import com.example.prisewatch.db.room.dao.ItemDao
import com.example.prisewatch.db.room.db.DataBase
import com.example.prisewatch.db.room.entities.ItemEntity
import com.example.prisewatch.db.room.entities.ItemWithPrices
import com.example.prisewatch.domain.model.DTOUtils
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalStateException

class ItemRepo private constructor(context: Context) {
  /*  private val db: DataBase = Room.databaseBuilder(
        context.applicationContext,
        DataBase::class.java,
        DataBase.NAME
    ).createFromAsset("initial").build()*/
  private val db: DataBase = Room.databaseBuilder(
        context.applicationContext,
        DataBase::class.java,
        DataBase.NAME
    ).build()

    private val itemDao = db.itemDao()
    private val priceDao = db.priceDao()

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

    fun getItems(): List<ItemWithPrices> {
        return itemDao.getAll()
    }

    @Transaction()
    fun insertItem(item: Item) {
        val fromItemToEntity = DTOUtils.fromItemToEntity(item)
        val idItem = itemDao.insert(fromItemToEntity.entity)[0]
        if (idItem == -1L) return
        fromItemToEntity.listPrice.forEach { it.itemId = idItem }
        priceDao.insert(fromItemToEntity.listPrice)
    }

    fun getById(idItem: Long): ItemWithPrices {
        return itemDao.getItemById(idItem)
    }
}