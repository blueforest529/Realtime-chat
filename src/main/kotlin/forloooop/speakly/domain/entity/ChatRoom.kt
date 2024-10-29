package forloooop.speakly.domain.entity

import jakarta.persistence.*

@Entity
data class ChatRoom(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val name: String,

    @OneToMany(mappedBy = "chatRoom", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val messages: MutableList<ChatMessage> = mutableListOf() // ChatMessage와의 일대다 관계
)