package forloooop.speakly.domain.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import forloooop.speakly.config.websocket.ChatRoomDeserializer
import forloooop.speakly.config.websocket.TopicDeserializer
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class ChatMessage @JsonCreator constructor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @JsonProperty("sender")
    val sender: String,

    @JsonProperty("content")
    val content: String,

    @JsonProperty("timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    @JsonProperty("type")
    var type: MessageType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @JsonDeserialize(using = ChatRoomDeserializer::class)
    @JsonProperty("room")
    val room: ChatRoom,

    @JsonDeserialize(using = TopicDeserializer::class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    @JsonProperty("topic")
    val topic: Topic,

    @JsonProperty("createdAt")
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    // 명시적인 기본 생성자 추가
    constructor() : this(
        id = 0L,
        sender = "",
        content = "",
        timestamp = System.currentTimeMillis(),
        type = MessageType.CHAT,
        room = ChatRoom(),  // 기본 생성자로 초기화
        topic = Topic(),  // 기본 생성자로 초기화
        createdAt = LocalDateTime.now()
    )
}


enum class MessageType {
    CHAT, JOIN, LEAVE
}