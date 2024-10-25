package forloooop.speakly.domain.entity.relation

import forloooop.speakly.domain.entity.Tag
import forloooop.speakly.domain.entity.Topic
import jakarta.persistence.*

@Entity
@Table(name = "topic_tag_relation")
class TopicTagRelation(
    @ManyToOne(optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    val topic: Topic = Topic(),

    @ManyToOne(optional = false)
    @JoinColumn(name = "tag_id", nullable = false)
    val tag: Tag = Tag(),
    tagId: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 혹은 원하는 생성 전략
    var id: Long? = null
}