package forloooop.speakly.config.redis

import forloooop.speakly.config.websocket.ChatSubscriber
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.messaging.simp.SimpMessagingTemplate


@Configuration
class RedisConfig {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory("localhost", 6379)
    }

    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("chatroom")
    }

    @Bean
    fun messageListener(template: SimpMessagingTemplate): MessageListenerAdapter {
        return MessageListenerAdapter(ChatSubscriber(template))
    }

    @Bean
    fun redisContainer(connectionFactory: RedisConnectionFactory, listenerAdapter: MessageListenerAdapter): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(connectionFactory)
        container.addMessageListener(listenerAdapter, topic())
        return container
    }
    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(connectionFactory)

        // Redis에서 key, value의 직렬화 방식을 설정
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = StringRedisSerializer() // 필요 시 다른 Serializer 사용 가능
        template.afterPropertiesSet()

        return template
    }

}