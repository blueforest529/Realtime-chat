package forloooop.speakly.config.redis

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import forloooop.speakly.application.service.RedisListenerService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.util.concurrent.Executors

@Configuration
class RedisConfig(
    private val redisListenerService: RedisListenerService
){

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory() // Redis와의 연결 설정
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>() // RedisTemplate 생성
        template.setConnectionFactory(redisConnectionFactory())

        // ObjectMapper에 JavaTimeModule 등록
        val objectMapper = ObjectMapper().apply {
            registerModule(JavaTimeModule())
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }

        // JSON 직렬화를 위해 Jackson2JsonRedisSerializer 설정
        val serializer = GenericJackson2JsonRedisSerializer(objectMapper)
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = serializer
        template.afterPropertiesSet()

        return template
    }

    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("chatChannel")
    }

    @Bean
    fun redisMessageListenerContainer(connectionFactory: RedisConnectionFactory): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(connectionFactory)
        container.addMessageListener(MessageListenerAdapter(redisListenerService), ChannelTopic("chatChannel"))
        container.setTaskExecutor(Executors.newFixedThreadPool(1)) // 스레드 풀 크기 제한
        return container
    }
}