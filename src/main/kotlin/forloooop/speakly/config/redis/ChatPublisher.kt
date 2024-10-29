package forloooop.speakly.config.redis

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import forloooop.speakly.domain.entity.ChatMessage
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class ChatPublisher(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val topic: ChannelTopic
) {
    private val objectMapper = jacksonObjectMapper()

    fun publish(chatMessage: ChatMessage) {
        val message = objectMapper.writeValueAsString(chatMessage)
        redisTemplate.convertAndSend(topic.topic, message)
    }
}