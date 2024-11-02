package forloooop.speakly.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import java.time.LocalDateTime
import jakarta.persistence.Id


@Entity
data class ChatMessageRedis(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val sender: String = "",
    val content: String = "",
    val timestamp: Long = 0L,
    val topic: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now()
)