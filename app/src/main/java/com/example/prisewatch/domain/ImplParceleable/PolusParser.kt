package com.example.prisewatch.domain.ImplParceleable

import com.example.myapplication.model.Item
import com.example.prisewatch.domain.webclient.WebClientable
import com.example.prisewatch.model.Price
import com.gargoylesoftware.htmlunit.html.HtmlPage
import org.jsoup.Jsoup
import java.util.*
import kotlin.collections.ArrayList

class PolusParser(webClient: WebClientable) : AbstractParser(webClient) {
    override suspend fun getParsedItems(urls: Array<String>): ArrayList<Item> {
        TODO("Not yet implemented")
    }

    override suspend fun getParsedItem(url: String): Item {
        val client = webClient.getWebClient()
        val page = client.getPage<HtmlPage>(url)
        val asXml = page.asXml()
        val parse = Jsoup.parse(asXml)
        val body = parse.body()
        val section = body.getElementsByClass("catalogue-detail")[0]

        val price = section.getElementsByAttributeValue("itemprop", "price").attr("content")
        val name = section.getElementsByAttributeValue("itemprop", "name")[0].text()
        val img = section.getElementsByAttribute("data-src-img").attr("data-src-img")

        return Item(imgUrl = img,title = name!!).also { it.listPrice.add(Price(0, price.toDouble(), Date()))}
    }
}