package forloooop.speakly.config.websocket

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.converter.MessageConverter
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/topic") // /topic 경로를 구독용으로 설정
        config.setApplicationDestinationPrefixes("/app") // /app 경로를 메시지 발행용으로 설정
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws")
            .setAllowedOriginPatterns("*") // 모든 출처 허용
            .withSockJS()
    }

    override fun configureMessageConverters(messageConverters: MutableList<MessageConverter>): Boolean {
        val converter = MappingJackson2MessageConverter()
        converter.objectMapper = jacksonObjectMapper()
            .registerModule(JavaTimeModule()) // Java 8 LocalDateTime 모듈 추가
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        messageConverters.add(converter)
        return false
    }
}