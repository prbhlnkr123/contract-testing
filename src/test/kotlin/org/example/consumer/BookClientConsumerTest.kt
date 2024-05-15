package org.example.consumer

import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.V4Pact
import au.com.dius.pact.core.model.annotations.Pact
import org.example.consumer.model.Book
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.client.RestTemplate


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [BookConsumerApplication::class])
@ExtendWith(PactConsumerTestExt::class, SpringExtension::class)
@AutoConfigureMockMvc
class BookClientConsumerTest() {

    private val bookResponseBody =
        PactDslJsonBody()
            .stringType("title")
            .stringType("author")

    @Pact(consumer = "bookClient", provider= "bookProvider")
    fun getBooks(builder: PactDslWithProvider): V4Pact {
        return builder
            .given("the book exist")
            .uponReceiving("get all books")
            .method("GET")
            .pathFromProviderState("/books/\${id}", "/books/100")
            .willRespondWith()
            .status(200)
            .body(bookResponseBody)
            .toPact(V4Pact::class.java)
    }

    @Test
    @PactTestFor(pactMethod = "getBooks")
    fun `test integration with book provider`(mockServer: MockServer) {
        val book: Book = Book(title = "test", author = "author")
        val response = RestTemplate().exchange(
            mockServer.getUrl() + "/books/100",
            HttpMethod.GET,
            HttpEntity(bookResponseBody),
            Any::class.java)
        assertNotNull(response.body)
    }
}
