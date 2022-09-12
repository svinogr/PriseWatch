package com.example.prisewatch.ui.vewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Item

class ShareItemModel : ViewModel() {
    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item> = _item

   /* fun parse(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val domain = URL(url).host.toString()
            Log.d("TAG", domain)
            val parser = ParserFabric().getParserByDomain(domain)
            val parsedItem = parser.getParsedItem(url)
            val urlImg = "$domain/${parsedItem.imgUrl}"
            parsedItem.imgUrl = urlImg
            _item.postValue(parsedItem)
            Log.d("TAG", "finish")

        }
    }*/
}