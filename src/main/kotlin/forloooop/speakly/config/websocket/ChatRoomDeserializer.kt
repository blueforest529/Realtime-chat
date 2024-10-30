package forloooop.speakly.config.websocket

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import forloooop.speakly.adapter.persistance.jpa.ChatRoomRepository
import forloooop.speakly.domain.entity.ChatRoom
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ChatRoomDeserializer @Autowired constructor(
    private val chatRoomRepository: ChatRoomRepository
) : JsonDeserializer<ChatRoom>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ChatRoom {
        val roomId = p.text.toLongOrNull()
            ?: throw IllegalArgumentException("Room ID is not a valid number: ${p.text}")

        return chatRoomRepository.findById(roomId)
            .orElseThrow { IllegalArgumentException("Invalid room ID: $roomId") }
    }
}