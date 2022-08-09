package com.example.prisewatch.domain.webclient.ImplWebClientable

import com.example.prisewatch.domain.webclient.WebClientable
import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.WebClient

class DNSWebClient: WebClientable {
    override fun getWebClient(): WebClient {
        val webClient = WebClient(BrowserVersion.getDefault())
        webClient.options.isThrowExceptionOnScriptError = false
        webClient.options.isJavaScriptEnabled = false
        webClient.options.isCssEnabled = false
       // webClient.waitForBackgroundJavaScript(5000);

        webClient.addRequestHeader("X-Requested-With", "XMLHttpRequest")
        webClient.addRequestHeader("Content-Type", "application/json")

        return webClient
    }
}