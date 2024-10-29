package forloooop.speakly.application.port.output

import forloooop.speakly.domain.entity.ChatMessage

interface ChatOutput {
    fun findMessages(roomId: String): List<ChatMessage>
    fun saveMessage(roomId: String, chatMessage: ChatMessage): ChatMessage
}
