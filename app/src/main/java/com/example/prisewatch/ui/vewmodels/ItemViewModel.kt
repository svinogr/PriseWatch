package com.example.prisewatch.ui.vewmodels

import android.os.Handler
import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.model.Item
import com.example.prisewatch.db.room.repo.ItemRepo
import com.example.prisewatch.domain.model.DTOUtils
import com.example.prisewatch.retrofit.ItemRetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.util.*
import javax.security.auth.callback.Callback

class ItemViewModel : ViewModel() {
    private var _itemList = MutableLiveData<List<Item>>()
    val itemList = _itemList

    fun getAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAG", "getAllItems")
            val toList = ItemRepo.get().getItems()
            Log.d("TAG", toList.toString())
            _itemList.postValue(toList.map { itemWithPrices ->
                DTOUtils.fromEntityToItem(
                    itemWithPrices
                )
            })
        }
    }


    fun getListItemsById() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAG", "getListItemsById")
            val itemListId = ItemRepo.get().getItems().map { e -> Item(e.entity.id) }
            Log.d("TAG", "list id ${itemListId.toString()}")

            ItemRetrofitService.get().getListItemsById(itemListId).enqueue(object: retrofit2.Callback<List<Item>>{
                override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                    val code = response.code()
                    if (code == 200) {
                        _itemList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                 Log.d("TAG", "${t.message}")
                }
            })

            //   itemList.postValue(listItemsById.value)

        }
    }

    fun testAdd() {
        viewModelScope.launch(Dispatchers.IO) {
            val repo = ItemRepo.get()
            val uuid = UUID.randomUUID()
            val item = com.example.myapplication.model.Item(
                0,
                "плавки",
                "http://www.baks.r2u/$uuid",
                imgUrl = uuid.toString(),
                host = "http://www.baks.r2u"
            )

            item.listPrice.add(
                com.example.prisewatch.domain.model.Price(
                    0,
                    item.id,
                    63.0,
                    Date().time
                )
            )

            repo.insertItem(item)
        }

    }
}