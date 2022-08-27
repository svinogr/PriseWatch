package com.example.prisewatch.ui.vewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Item
import com.example.prisewatch.db.room.db.DataBase
import com.example.prisewatch.db.room.entities.ItemEntity
import com.example.prisewatch.db.room.repo.ItemRepo
import com.example.prisewatch.domain.ImplParserFabricable.ParserFabric
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.stream.Collectors.toList

class ItemViewModel : ViewModel() {
    private var _itemList = MutableLiveData<List<Item>>()
    var itemList = _itemList

    fun getAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            /* val fabric = ParserFabric()
             val parserByDomain = fabric.getParserByDomain("www.kasla.ru")
             val item =
                 //parserByDomain.getParsedItem("https://www.citilink.ru/product/pamyat-ddr4-16gb-2666mhz-crucial-cb16gs2666-oem-pc4-21300-cl16-so-dimm-1430548/")
                 parserByDomain.getParsedItem("https://kasla.ru/catalog/accessories/zaryadnye_ustroystva_i_adaptery/avtomobilnoe_zaryadnoe_ustroystvo_anker_power_drive_2_ru.html?offerID=5339")

             Log.d("TAG", "$item")
             val list = mutableListOf<Item>().apply { add(item) }
          //   ItemRepo.get().getItems()
             ItemRepo.get().insertItem(
                 ItemEntity(id = 0, title = item.title, price = item.price, urlImg = item.imgUrl)
             )*/
            /*       ItemRepo.get().insertItem(
                       ItemEntity(id = 0, title = "dwdwd", price = 0.0, urlImg = "dwdwd")
                   )*/

            //val list = ItemRepo.get().getItems().stream().map { Item(it.id, "${it.title} ${it.id}", "", it.price, it.urlImg) }.collect(toList())
            val items = ItemRepo.get().getItems()
            Log.d("TAG", "$items")
            _itemList.postValue(items.map { it.entityToItem() })
        }

    }
}