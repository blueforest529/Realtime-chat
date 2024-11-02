package forloooop.speakly.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class ChatMessage (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val sender: String,
    val content: String,
    val timestamp: Long,
    val topic: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
}
