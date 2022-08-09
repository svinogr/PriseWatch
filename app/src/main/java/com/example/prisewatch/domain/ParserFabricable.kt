package com.example.prisewatch.domain

interface ParserFabricable {
    fun getParserByDomain(domain: String): Parserable
}