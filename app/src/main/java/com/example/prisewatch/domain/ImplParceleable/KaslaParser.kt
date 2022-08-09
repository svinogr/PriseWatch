package com.example.prisewatch.domain.ImplParceleable

import com.example.myapplication.model.Item
import com.example.prisewatch.domain.webclient.WebClientable
import com.gargoylesoftware.htmlunit.html.HtmlPage
import org.jsoup.Jsoup

class KaslaParser(webClientable: WebClientable) : AbstractParser(webClientable) {
    override suspend fun getParsedItems(urls: Array<String>): ArrayList<Item> {
        TODO("Not yet implemented")
    }

    override suspend fun getParsedItem(url: String): Item {
        val client = webClient.getWebClient()
        val page = client.getPage<HtmlPage>(url)
        val asXml = page.asXml()
        val parse = Jsoup.parse(asXml)

        val body = parse.body()
        val section = body.getElementById("tableContainer")

        val img = section?.getElementById("pictureContainer")?.getElementsByTag("a")?.attr("href").toString()
        val title = body.getElementById("right")?.getElementsByTag("h1")?.html()
        val price = section?.getElementById("elementTools")!!.getElementsByClass("priceVal")[0].text().
        split("руб")[0].trim().replace(" ", "").toDouble()

        return Item(img = img,title = title!!, price = price)
    }
}