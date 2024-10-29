package forloooop.speakly.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class ChatMessage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val sender: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    var type: MessageType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    val room: ChatRoom,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id") // 외래 키 생성
    val chatRoom: ChatRoom,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    val topic: Topic, // Topic 필드 추가

    val createdAt: LocalDateTime = LocalDateTime.now() // 생성 시각 필드 추가
)

enum class MessageType {
    CHAT, JOIN, LEAVE
}
