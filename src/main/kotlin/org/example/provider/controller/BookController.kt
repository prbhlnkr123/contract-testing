package org.example.provider.controller

import org.example.provider.model.Book
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController {
    @GetMapping("/books")
    fun getBook(): Book {
        return Book("Sample Book", "John Doe")
    }
}
