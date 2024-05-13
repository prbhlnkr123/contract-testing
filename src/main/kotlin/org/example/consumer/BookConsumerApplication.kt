package org.example.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class BookConsumerApplication {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<BookConsumerApplication>(*args)
        }
    }
}
