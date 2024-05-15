package org.example.provider.controller

import org.example.provider.BookService
import org.example.provider.model.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController {
    @Autowired
    lateinit var bookService: BookService

    @GetMapping("/books")
    fun getBook(): Book {
        return bookService.getBook()
    }
}
