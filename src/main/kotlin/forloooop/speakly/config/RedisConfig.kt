package forloooop.speakly.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.messaging.simp.SimpMessagingTemplate


@Configuration
class RedisConfig {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory("localhost", 6379)
    }

    @Bean
    fun messageListener(template: SimpMessagingTemplate): MessageListenerAdapter {
        return MessageListenerAdapter(ChatMessageSubscriber(template))
    }

    @Bean
    fun redisContainer(connectionFactory: RedisConnectionFactory, listenerAdapter: MessageListenerAdapter): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(connectionFactory)
        container.addMessageListener(listenerAdapter, ChannelTopic("chatroom"))
        return container
    }

    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("chatroom")
    }
}