package com.example.prisewatch.ui.vewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.model.Item
import com.example.prisewatch.db.room.repo.ItemRepo
import com.example.prisewatch.retrofit.ItemRetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.util.*

class ItemViewModel : ViewModel() {
    private var _itemList = MutableLiveData<List<Item>>()
    val itemList: LiveData<List<Item>> = _itemList
    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

       fun getListItemsById() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAG", "getListItemsById")
            _progress.postValue(true)
            val itemListId = ItemRepo.get().getItems().map { e -> Item(e.entity.id) }
            Log.d("TAG", "list id ${itemListId.toString()}")

            ItemRetrofitService.get().getListItemsById(itemListId).enqueue(object: retrofit2.Callback<List<Item>>{
                override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                    val code = response.code()
                    if (code == 200) {
                        _itemList.postValue(response.body())
                    }
                    _progress.postValue(false)

                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                 Log.d("TAG", "${t.message}")
                    _progress.postValue(false)

                }
            })

            //   itemList.postValue(listItemsById.value)

        }
    }


    fun onlyForTestInflateDB() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAG", "onlyForTestInflateDB")
            _progress.postValue(true)


            ItemRetrofitService.get().getAll().enqueue(object: retrofit2.Callback<List<Item>>{
                override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                    val code = response.code()
                    if (code == 200) {
                        response.body()?.forEach {
                           viewModelScope.launch(Dispatchers.IO) {
                               ItemRepo.get().insertItem(it)
                           }
                        }
                        _itemList.postValue(response.body())
                    }
                    _progress.postValue(false)

                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    Log.d("TAG", "${t.message}")
                    _progress.postValue(false)

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
                imgLink = uuid.toString(),
                host = "http://www.baks.r2u"
            )

            item.priceList.add(
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