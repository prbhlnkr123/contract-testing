package org.example.consumer.client

import org.example.consumer.model.Book
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class BookClient(private val restTemplate: RestTemplate, @Value("\${book.provider.url}") private val baseUrl: String) {

    fun getBook(): Book {
        val response = restTemplate.getForObject("$baseUrl/books", Book::class.java)
        return response!!
    }
}
