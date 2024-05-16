package org.example.provider

import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth
import org.example.provider.model.Book
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("bookProvider")
@PactBroker(url = "https://mycompanyt.pactflow.io", authentication = PactBrokerAuth(token = "3y6m3pBVs9q8HNJ4-sC6tg"))
class BookProviderTest {

    @MockBean
    lateinit var bookService: BookService

    @BeforeEach
    fun setup() {
        System.setProperty("pact.provider.version", "1.0")
        System.setProperty("pact.verifier.publishResults", "true")
    }
    @Test
    @State("the book exist")
    fun getBookVerificationTest() {
        val book = Book("test book", "test")
        `when`(bookService.getBook()).thenReturn(book)
    }
}