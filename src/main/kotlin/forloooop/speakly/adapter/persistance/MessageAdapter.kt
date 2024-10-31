package forloooop.speakly.adapter.persistance

import com.fasterxml.jackson.databind.ObjectMapper
import forloooop.speakly.application.service.ChatService
import forloooop.speakly.domain.entity.ChatMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component


@Component
class MessageAdapter(
    private val messagingTemplate: SimpMessagingTemplate,
    private val objectMapper: ObjectMapper,
    private val chatService: ChatService
) {

    @MessageMapping("/sendMessage")
    fun receiveMessage(payload: String) {
        try {
            val message = objectMapper.readValue(payload, ChatMessage::class.java) // JSON 역직렬화
            val savedMessage = chatService.processMessage(message)
            messagingTemplate.convertAndSend("/topic/messages", savedMessage)
        } catch (e: Exception) {
            println("Deserialization error: ${e.message}")  // 역직렬화 오류 로그 출력
        }
    }
}