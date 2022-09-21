package com.example.prisewatch.ui.vewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.prisewatch.db.room.entities.ItemWithPrices
import com.example.prisewatch.db.room.repo.ItemRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ItemDetailViewModel : ViewModel() {
    private var _item = MutableLiveData<ItemWithPrices>()
    val item: LiveData<ItemWithPrices> = _item

    private var _progress = MutableLiveData<Boolean>()
    val progress = _progress

    fun findById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            progress.postValue(true)
            _item.postValue(ItemRepo.get().getById(id))
            progress.postValue(false)
        }
    }
}
