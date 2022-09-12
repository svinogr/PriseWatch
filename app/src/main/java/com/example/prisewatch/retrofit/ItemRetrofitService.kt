package com.example.prisewatch.retrofit

import androidx.lifecycle.LiveData
import com.example.myapplication.model.Item
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

const val BASE_URL = "http://10.0.2.2:8080"

interface ItemRetrofitService  {

    companion object {
        private var instance: ItemRetrofitService? = null

        fun initialize() {
            if (instance == null) {
                val retrofit = Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build()
                instance = retrofit.create(ItemRetrofitService::class.java)
            }
        }

        fun get() : ItemRetrofitService {
            if (instance == null) initialize()

            return instance!!
        }
    }

    @POST("/items")
    fun getListItemsById(@Body list: List<Item>) : Call<List<Item>>
}