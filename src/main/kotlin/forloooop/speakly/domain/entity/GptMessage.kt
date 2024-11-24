package forloooop.speakly.domain.entity

import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "chat_message")
class GptMessage {

    @Id
    @Tsid
    var id: Long? = null

    @Column(nullable = false)
    var message: String = ""
}
