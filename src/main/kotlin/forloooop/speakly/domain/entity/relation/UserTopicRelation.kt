package forloooop.speakly.domain.entity.relation

import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.entity.Topic
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_topic_relation")
class UserTopicRelation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "account_id")
    val account: Account,

    @ManyToOne
    @JoinColumn(name = "topic_id")
    val topic: Topic,

    val joinedAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(account = Account(), topic = Topic())
}
