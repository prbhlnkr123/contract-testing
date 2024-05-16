package org.example.provider

import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import au.com.dius.pact.provider.junitsupport.target.TestTarget
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth
import org.example.provider.model.Book
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = ["server.port=8080"], classes =  [BookProviderApplication::class])
@Provider("bookProvider")
@PactBroker(
    url = "https://mycompanyt.pactflow.io",
    authentication = PactBrokerAuth(token = "3y6m3pBVs9q8HNJ4-sC6tg"),
    consumers = ["bookClient"])
class BookProviderTest {

    @MockBean
    lateinit var bookService: BookService

    @TestTarget
    lateinit var target: HttpTestTarget

    @BeforeEach
    fun before(context: PactVerificationContext) {
        target = HttpTestTarget("localhost", 8080)
        context.target = target
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun testTemplate(context: PactVerificationContext) {
        System.setProperty("pact.verifier.publishResults", "true")
        context.verifyInteraction()
    }

    @Test
    @State("the book exist")
    fun getBookVerificationTest() {
        val book = Book(title="test book", author="test")
        `when`(bookService.getBook()).thenReturn(book)
    }
}