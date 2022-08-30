package com.example.prisewatch.ui.vewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.prisewatch.db.room.repo.ItemRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ItemViewModel : ViewModel() {
    private var _itemList = ItemRepo.get().getItems().asLiveData(viewModelScope.coroutineContext)
    var itemList = _itemList

    fun testAdd() {
        viewModelScope.launch(Dispatchers.IO) {
            val repo = ItemRepo.get()
            val uuid = UUID.randomUUID()
            val item = com.example.myapplication.model.Item(
                0,
                "плавки",
                "http://www.baks.r2u/$uuid",
                imgUrl = uuid.toString()
            )
            item.listPrice.add(com.example.prisewatch.domain.model.Price(0, 63.0, java.util.Date()))

            repo.insertItem(item)
        }

    }
}