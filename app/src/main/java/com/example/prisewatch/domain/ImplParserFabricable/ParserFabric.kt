package com.example.prisewatch.domain.ImplParserFabricable

import com.example.prisewatch.domain.ImplParceleable.CTLParser
import com.example.prisewatch.domain.ImplParceleable.DnsParser
import com.example.prisewatch.domain.ImplParceleable.KaslaParser
import com.example.prisewatch.domain.ImplParceleable.PolusParser
import com.example.prisewatch.domain.ParserFabricable
import com.example.prisewatch.domain.Parserable
import com.example.prisewatch.domain.webclient.ImplWebClientable.CityWebParser
import com.example.prisewatch.domain.webclient.ImplWebClientable.DNSWebClient
import com.example.prisewatch.domain.webclient.ImplWebClientable.KaslaWebClient
import com.example.prisewatch.domain.webclient.ImplWebClientable.PolusWebClient

class ParserFabric : ParserFabricable {
    override fun getParserByDomain(domain: String): Parserable {
        return when (domain) {
            "dns-shop.ru" -> {
                val dns = DNSWebClient()
                DnsParser(dns)
            }
            "citilink.ru" -> {
                val ctl = CityWebParser()
                CTLParser(ctl)
            }

            "polus.ru" -> {
                PolusParser(PolusWebClient())
            }
            "kasla.ru" -> {
                KaslaParser(KaslaWebClient())
            }


            else -> throw Exception("this domain is not support")
        }
    }
}