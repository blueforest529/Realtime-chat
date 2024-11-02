package forloooop.speakly.adapter.web

import forloooop.speakly.application.service.ChatService
import forloooop.speakly.domain.entity.ChatMessage
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Controller
class ChatController(
    private val messagingTemplate: SimpMessagingTemplate,
    private val chatService: ChatService,
    private val redisTemplate: RedisTemplate<String, Any>
) {

    @MessageMapping("/sendMessage")
    fun receiveMessage(@Payload message: ChatMessage) {
        try {
            val savedMessage = chatService.processMessage(message)

            messagingTemplate.convertAndSend("/topic/messages", savedMessage)
            
            // Redis에 메시지 발행
            redisTemplate.convertAndSend("chatChannel", savedMessage)
        } catch (e: Exception) {
            println("Error processing message: ${e.message}")
            e.printStackTrace()
        }
    }
}
