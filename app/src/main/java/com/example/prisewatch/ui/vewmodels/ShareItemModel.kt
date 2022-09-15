package com.example.prisewatch.ui.vewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Item
import com.example.prisewatch.db.room.repo.ItemRepo
import com.example.prisewatch.domain.model.DTOUtils
import com.example.prisewatch.retrofit.ItemRetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.util.*

class ShareItemModel : ViewModel() {
    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item> = _item
    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    fun getItem(urlString: String) {
        // проверяем есть ли в базе
        // если есть то проверяем дату и если дата старая то отправляем запрос по id и выдем тост
        // если нет в базе то отпавляем запрос по URL
        _progress.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val itemEntity = ItemRepo.get().getByUrl(urlString)
            // проверяем есть ли в базе уже
            if (itemEntity != null) {
                val item = DTOUtils.fromEntityToItem(itemEntity)
                if (!dateIsNotOld(item)) {
                    Log.d("TAG", "getFromServerById")
                    getFromServerById(item)
                }
            } else {
                Log.d("TAG", "getFromServerByUrl")
                getFromServerByUrl(urlString)
            }
        }
    }

    private fun getFromServerByUrl(urlStr: String) {
        viewModelScope.launch(Dispatchers.IO) {
            ItemRetrofitService.get().getItemByUrl(Item(urlLink = urlStr))
                .enqueue(object : retrofit2.Callback<Item> {
                    override fun onResponse(call: Call<Item>, response: Response<Item>) {
                        Log.d("TAG", "resp f url ${response.body()}")
                        Log.d("TAG", "resp f url ${response.code()}")
                        val code = response.code()
                        if (code == 200) {
                            response.body()?.let {
                                Log.d("TAG", "item from server $it")
                                Log.d("TAG", "item from server ${it.priceList[0].price}")
                                _item.postValue(it)
                                _progress.postValue(false)
                            }
                        }
                    }

                    override fun onFailure(call: Call<Item>, t: Throwable) {
                        Log.d("TAG", "${t.message}")
                    }
                })
        }
    }

    fun saveToDb() {
        viewModelScope.launch(Dispatchers.IO) {
            item.value.let {
                ItemRepo.get().insertItem(it!!)
            }
        }
    }

    private fun getFromServerById(item: Item) {
        ItemRetrofitService.get().getListItemsById(listOf(item))
            .enqueue(object : retrofit2.Callback<List<Item>> {
                override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                    val code = response.code()
                    if (code == 200) {
                        response.body()?.let {
                            if (it.isNotEmpty()) {
                                _item.postValue(it[0])
                            }
                        }
                    }


                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    Log.d("TAG", "${t.message}")


                }
            })

    }

    private fun dateIsNotOld(item: Item): Boolean {
        val now = Calendar.getInstance()
        val last = Calendar.getInstance()
        last.time = Date(item.lastPrice().date)

        return !last.before(now)
    }

}