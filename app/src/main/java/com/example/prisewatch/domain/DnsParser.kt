package com.example.prisewatch.domain

import android.util.Log
import com.example.myapplication.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.regex.Pattern

class DnsParser {

    // запуск  GlobalScope.launch(Dispatchers.IO) {
    //            createDNSParser()
    //        }

    private suspend fun createDNSParser() {
        val url =
            "https://www.dns-shop.ru/product/d00d5ac5b4852eb1/videokarta-gigabyte-geforce-rtx-3070-ti-aorus-master-gv-n307taorus-m-8gd/"

        val webClient: WebClient = getWebClient()
        val page = webClient.getPage<Page>(url)
        val webResponse = page.webResponse
        val product = Item()
        if (webResponse.contentType == "application/json") {
            var jsonString = webResponse.contentAsString
            val indexOfStartOfStringSendProductMessage = jsonString.indexOf("sendProductMessage")
            jsonString = jsonString.substring(indexOfStartOfStringSendProductMessage + "sendProductMessage".length + 1)

            /*      val pattern = Pattern.compile("monthWarranty.+?}".))
                  val matcher = pattern.matcher(jsonString)

     */
            val pattern = "monthWarranty.+?\\}".toRegex()
            val find = pattern.find(jsonString)

            if (find != null)
                jsonString = jsonString.substring(0, find.range.last + 1)
            print(jsonString)
            jsonString = jsonString.replace("\\\\\"".toRegex(), "\"")
            print(jsonString)
            val json  = JSONObject(jsonString)

            product.title = json.getString("name")
            product.price = json.getString("price").toDouble()

        }

        GlobalScope.launch(Dispatchers.Main) {
            binding.textviewSecond.text = product.title + product.price
        }


    }
    private suspend fun createDNSParser2() {
        try {
            val url =
                "https://www.dns-shop.ru/product/d00d5ac5b4852eb1/videokarta-gigabyte-geforce-rtx-3070-ti-aorus-master-gv-n307taorus-m-8gd/"

            val webClient: WebClient = getWebClient()



            val page: Page = webClient.getPage(url)
            val webResponse = page.webResponse
            val product = Item()
            if (webResponse.contentType == "application/json") {
                var jsonString = webResponse.contentAsString
                val indexOfStartOfStringSendProductMessage =
                    jsonString.indexOf("sendProductMessage")
                Log.d("createDNSParser2", " start $indexOfStartOfStringSendProductMessage")
                print(" start $indexOfStartOfStringSendProductMessage")
                jsonString =
                    jsonString.substring(indexOfStartOfStringSendProductMessage + "sendProductMessage".length + 1)

                val pattern = Pattern.compile("monthWarranty.+?}")
                val matcher = pattern.matcher(jsonString)


                if (matcher.find()) {
                    jsonString = jsonString.substring(0, matcher.end())
                    jsonString = jsonString.replace("\\\\\"".toRegex(), "\"")

                    val json = JSONObject(jsonString)

                    product.title = json.getString("name")
                    product.price = json.getString("price").toDouble()

                    GlobalScope.launch(Dispatchers.Main) {
                        binding.textviewSecond.text = product.toString()
                    }
                }
            }
        }catch (e: Exception) {
            print(e.message)
        }



    }

    /*var htmlPageFromWebClient: HtmlPage? = null
    try {
        htmlPageFromWebClient = getHtmlPageFromWebClient(webClient, url)
    } catch (e: IOException) {

    }

    val idUrlItem: String = getIdUrlItem(htmlPageFromWebClient!!)

    //val productPrice: Item = getProductPrice(url2, webClient, idUrlItem)

    System.out.println(productPrice)*/


    private suspend fun getProductPrice(
        url2: String,
        webClient: WebClient,
        idUrlItem: String
    ): Item {
        val price = Item()
        webClient.addRequestHeader("Content-Type", "application/json")
        val jsonPage: Page
        try {
            //System.out.println(idUrlItem);
            jsonPage = webClient.getPage(url2 + idUrlItem)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        val response: WebResponse = jsonPage.getWebResponse()
        if (response.contentType == "application/json") {
            val json = response.contentAsString
            //  System.out.println(json);
            val j = JSONObject(json)
            val data = j.getJSONObject("data")
            price.price = data.getJSONObject("offers")["price"].toString().toDouble()
            price.title = data["name"].toString()
        }
        return price
    }

    private suspend fun getIdUrlItem(page: HtmlPage): String {
        val parse: Document = Jsoup.parse(page.asXml())
        return parse.getElementsByAttribute("data-product-card").attr("data-product-card")
    }

    private suspend fun getHtmlPageFromWebClient(webClient: WebClient, url: String): HtmlPage {
        webClient.options.isThrowExceptionOnScriptError = false
        return webClient.getPage(url)
    }

    private suspend fun getWebClient(): WebClient {
        val webClient = WebClient(BrowserVersion.INTERNET_EXPLORER)
        webClient.options.isThrowExceptionOnScriptError = true
        webClient.options.isJavaScriptEnabled = false
        webClient.options.isCssEnabled = false
        webClient.waitForBackgroundJavaScript(500);

        webClient.addRequestHeader("X-Requested-With", "XMLHttpRequest")

        return webClient
    }
}