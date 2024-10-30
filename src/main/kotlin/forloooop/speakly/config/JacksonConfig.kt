package forloooop.speakly.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


//역직렬화
@Configuration
class JacksonConfig {
    @Bean
    fun javaTimeModule() = JavaTimeModule()

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().registerModule(JavaTimeModule())
    }
}