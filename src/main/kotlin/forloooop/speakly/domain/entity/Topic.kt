package forloooop.speakly.domain.entity

import forloooop.speakly.domain.entity.relation.TopicTagRelation
import forloooop.speakly.domain.entity.relation.UserTopicRelation
import forloooop.speakly.domain.interfaces.Searchable
import forloooop.speakly.domain.search.TopicSearch
import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "topic")
class Topic : Searchable<TopicSearch> {

    @Id
    @Tsid
    var id: Long? = null

    @Column(nullable = false)
    var name: String = ""

    @Column
    var description: String? = null

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(nullable = false)
    var numberOfAttendee: Int = 0

    @Column(nullable = false)
    var isPublic: Boolean = true

    @ManyToOne
    @JoinColumn(name = "account_id")
    var owner: Account? = null

    @OneToMany(mappedBy = "topic", cascade = [CascadeType.ALL], orphanRemoval = true)
    var topicTagRelationGroup : List<TopicTagRelation> = mutableListOf()

    @OneToMany(mappedBy = "topic", cascade = [CascadeType.ALL], orphanRemoval = true)
    var userTopicRelationGroup : List<UserTopicRelation> = mutableListOf()

    override fun toSearch(): TopicSearch {
        return TopicSearch().apply {
            name = this@Topic.name
            description = this@Topic.description
            numberOfAttendee = this@Topic.numberOfAttendee
            isPublic = this@Topic.isPublic
        }
    }
}
