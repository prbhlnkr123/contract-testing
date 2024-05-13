package org.example.provider

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookProviderApplication

fun main(args: Array<String>) {
    runApplication<BookProviderApplication>(*args)
}
