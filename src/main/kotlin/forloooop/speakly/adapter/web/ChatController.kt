package forloooop.speakly.adapter.web

import forloooop.speakly.application.service.ChatService
import forloooop.speakly.domain.entity.ChatMessage
import forloooop.speakly.domain.entity.MessageType
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class ChatController(
    private val chatService: ChatService
) {

    @GetMapping
    fun getAllMessages(): List<ChatMessage> {
        return chatService.getAllMessages()
    }

    @PostMapping
    fun sendMessage(@RequestBody chatMessage: ChatMessage): ChatMessage {
        return chatService.processMessage(chatMessage)
    }
}