package forloooop.speakly.application.service

import forloooop.speakly.adapter.persistance.jpa.ChatMessageRepository
import forloooop.speakly.adapter.persistance.jpa.ChatRepository
import forloooop.speakly.adapter.persistance.jpa.ChatRoomRepository
import forloooop.speakly.domain.entity.ChatMessage
import forloooop.speakly.domain.entity.ChatRoom
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ChatService(
    private val chatMessageRepository: ChatMessageRepository,
    private val chatRoomRepository: ChatRoomRepository,
    private val chatRepository: ChatRepository

) {

    // 채팅방 생성
    fun createRoom(name: String): ChatRoom {
        val chatRoom = ChatRoom(name = name)
        return chatRoomRepository.save(chatRoom)
    }

    // 메시지 전송
    fun sendMessage(roomId: String, chatMessage: ChatMessage): ChatMessage {
        // 메시지를 저장하고 반환
        return chatMessageRepository.save(chatMessage)
    }

    // 채팅방의 모든 메시지 조회
    fun getMessages(room: ChatRoom): List<ChatMessage> {
        return chatMessageRepository.findByRoom(room)
    }

    fun saveMessage(chatMessage: ChatMessage): ChatMessage {
        return chatRepository.save(chatMessage) // 직접 save 메서드를 사용
    }

//    // 특정 시간 이후의 메시지 조회
//    fun getRecentMessages(roomId: String, timestamp: LocalDateTime): List<ChatMessage> {
//        return chatMessageRepository.findByRoomIdAndTimestampAfter(chatroom, timestamp)
//    }

//    // 특정 시간 이후의 메시지 개수 조회
//    fun countRecentMessages(roomId: String, timestamp: LocalDateTime): Long {
//        return chatMessageRepository.countByRoomIdAndTimestampAfter(roomId, timestamp)
//    }

    // 메시지 삭제
    fun deleteMessage(chatMessageId: Long) {
        chatMessageRepository.deleteById(chatMessageId)
    }
}