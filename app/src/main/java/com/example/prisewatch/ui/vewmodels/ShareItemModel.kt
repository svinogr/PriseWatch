package com.example.prisewatch.ui.vewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Item
import com.example.prisewatch.domain.ImplParserFabricable.ParserFabric
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class ShareItemModel : ViewModel() {
    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item> = _item

    fun parse(url: String) {
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
    }
}