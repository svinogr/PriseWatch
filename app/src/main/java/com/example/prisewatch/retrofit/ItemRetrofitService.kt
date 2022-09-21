package com.example.prisewatch.retrofit

import androidx.lifecycle.LiveData
import com.example.myapplication.model.Item
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.time.Duration
import java.util.concurrent.TimeUnit

const val BASE_URL = "http://10.0.2.2:8080"

interface ItemRetrofitService {

    companion object {
        private var instance: ItemRetrofitService? = null

        fun initialize() {
            if (instance == null) {
                val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(120L, TimeUnit.SECONDS)
                    .readTimeout(120L, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                instance = retrofit.create(ItemRetrofitService::class.java)
            }
        }

        fun get(): ItemRetrofitService {
            if (instance == null) initialize()

            return instance!!
        }
    }

    @POST("/items")
    fun getListItemsById(@Body list: List<Item>): Call<List<Item>>

    @POST("items/url")
    fun getItemByUrl(@Body item: Item): Call<Item>

    @GET("items/all")
    fun getAll(): Call<List<Item>>
}