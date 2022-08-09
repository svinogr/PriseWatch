package com.example.prisewatch.domain

import com.example.myapplication.model.Item

interface Parserable {
    suspend fun getParsedItems(urls: Array<String>): ArrayList<Item>
    suspend fun getParsedItem(url: String): Item
}