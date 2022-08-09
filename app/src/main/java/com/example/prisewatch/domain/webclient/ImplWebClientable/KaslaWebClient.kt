package com.example.prisewatch.domain.webclient.ImplWebClientable

import com.example.prisewatch.domain.webclient.WebClientable
import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.WebClient

class KaslaWebClient : WebClientable {
    override fun getWebClient(): WebClient {
        val webClient = WebClient(BrowserVersion.getDefault())
        webClient.options.isThrowExceptionOnScriptError = false
        webClient.options.isJavaScriptEnabled = false
        webClient.options.isCssEnabled = false

        return webClient
    }

}
