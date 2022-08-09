package com.example.prisewatch.domain.ImplParceleable

import com.example.prisewatch.domain.Parserable
import com.example.prisewatch.domain.webclient.WebClientable

abstract class AbstractParser(val webClient: WebClientable) : Parserable {
}