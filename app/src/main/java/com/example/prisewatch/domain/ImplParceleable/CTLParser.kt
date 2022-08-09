package com.example.prisewatch.domain.ImplParceleable

import android.util.Log
import com.example.myapplication.model.Item
import com.example.prisewatch.domain.Parserable
import com.example.prisewatch.domain.webclient.WebClientable
import com.gargoylesoftware.htmlunit.Page
import com.gargoylesoftware.htmlunit.html.HtmlPage
import org.jsoup.Jsoup

class CTLParser(val webClient: WebClientable) : Parserable {
    override suspend fun getParsedItems(urls: Array<String>): ArrayList<Item> {
        return ArrayList()
    }

    override suspend fun getParsedItem(url: String): Item {
       TODO()
    }
}