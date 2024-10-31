package forloooop.speakly.application.service

import forloooop.speakly.adapter.persistance.jpa.ChatMessageRepository
import forloooop.speakly.domain.entity.ChatMessage
import org.springframework.stereotype.Service


@Service
class ChatService(
    private val messageRepository: ChatMessageRepository
) {
    fun processMessage(message: ChatMessage): ChatMessage {
        return messageRepository.save(message)  // 메시지를 DB에 저장하고 반환
    }

    fun getAllMessages(): List<ChatMessage> {
        return messageRepository.findAll()
    }
}