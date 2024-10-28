package forloooop.speakly.adapter.web

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller


@Controller
class ChatController(private val chatPublisher: ChatPublisher) {

    @MessageMapping("/chat.sendMessage")
    fun sendMessage(chatMessage: ChatMessage) {
        chatPublisher.publish(chatMessage) // Redis로 메시지 퍼블리시
    }

    @MessageMapping("/chat.addUser")
    fun addUser(chatMessage: ChatMessage) {
        chatMessage.type = MessageType.JOIN
        chatPublisher.publish(chatMessage)
    }
}