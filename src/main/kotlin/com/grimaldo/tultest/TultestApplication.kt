package com.grimaldo.tultest

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.util.*

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableJpaAuditing
class TultestApplication{

    @Bean
    @Primary
    fun jacksonObjectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerModules( KotlinModule())
            .setTimeZone(TimeZone.getDefault())
    }

    @Bean
    @Primary
    fun jackson2ObjectMapperBuilder(): Jackson2ObjectMapperBuilder? {
        return Jackson2ObjectMapperBuilder()
            .timeZone(TimeZone.getDefault())
            .serializationInclusion(JsonInclude.Include.NON_EMPTY)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }
}

fun main(args: Array<String>) {
    runApplication<TultestApplication>(*args)
}
