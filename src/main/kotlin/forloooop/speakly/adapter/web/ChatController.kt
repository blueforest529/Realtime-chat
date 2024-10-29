package forloooop.speakly.adapter.web

import forloooop.speakly.application.service.ChatService
import forloooop.speakly.config.redis.ChatPublisher
import forloooop.speakly.domain.entity.ChatMessage
import forloooop.speakly.domain.entity.MessageType
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class ChatController(
    private val chatService: ChatService,
    private val chatPublisher: ChatPublisher
) {

    @MessageMapping("/chat.sendMessage")
    fun sendMessage(chatMessage: ChatMessage) {
        chatService.sendMessage("public", chatMessage) // DB 또는 캐시에 저장
        chatPublisher.publish(chatMessage) // Redis에 퍼블리시하여 브로드캐스트
    }

    @MessageMapping("/chat.addUser")
    fun addUser(chatMessage: ChatMessage) {
        chatMessage.type = MessageType.JOIN
        chatPublisher.publish(chatMessage)
    }
}