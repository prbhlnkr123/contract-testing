package org.example.provider

import org.example.provider.model.Book
import org.springframework.stereotype.Service

@Service
class BookService {
    fun getBook(): Book {
        return Book("Sample Book", "John Doe")
    }
}