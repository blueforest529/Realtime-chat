package forloooop.speakly.domain.entity

import forloooop.speakly.domain.entity.relation.TopicTagRelation
import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.*

@Entity
@Table(name = "tag")
class Tag(
    @Column(nullable = false)
    var name: String = "",
) {

    @Id
    @Tsid
    var id: Long? = null

    @OneToMany(mappedBy = "tag", cascade = [CascadeType.ALL], orphanRemoval = true)
    var topicTagRelationGroup : List<TopicTagRelation> = mutableListOf()
}
