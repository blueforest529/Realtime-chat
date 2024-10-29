package forloooop.speakly.config.websocket

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import forloooop.speakly.domain.entity.ChatMessage
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.messaging.simp.SimpMessagingTemplate

import org.springframework.stereotype.Component

@Component
class ChatSubscriber(private val template: SimpMessagingTemplate) : MessageListener {
    private val objectMapper = jacksonObjectMapper()

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val chatMessage = objectMapper.readValue(message.body, ChatMessage::class.java)
        template.convertAndSend("/topic/public", chatMessage)
    }
}