package org.example.provider

import org.example.provider.model.Book
import org.springframework.stereotype.Service

@Service
class BookService {

    private val books = mutableListOf<Book>()

    fun getBook(): Book {
        return Book("Sample Book", "John Doe")
    }

    fun createBook(book: Book): Book {
        books.add(book)
        return book
    }
}