package com.example.prisewatch.domain.webclient

import com.gargoylesoftware.htmlunit.WebClient

interface WebClientable {
    fun getWebClient(): WebClient
}