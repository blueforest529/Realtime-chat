package forloooop.speakly.domain.entity

import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "chat_message")
class ChatMessage {

    @Id
    @Tsid
    var id: Long? = null

    val accountId: Long = 0

    @Column(nullable = false)
    var content: String = ""

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(nullable = true)
    var updatedAt: LocalDateTime? = null

    @ManyToOne
    @JoinColumn(name = "topic_id")
    lateinit var topic: Topic
}
