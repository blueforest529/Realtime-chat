package forloooop.speakly.adapter.persistance.jpa

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import forloooop.speakly.domain.entity.relation.UserTopicRelation
import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.entity.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserTopicRelationRepository : JpaRepository<UserTopicRelation, Long>, KotlinJdslJpqlExecutor {
    fun existsByAccountAndTopic(account: Account, topic: Topic): Boolean
    fun findByAccountAndTopic(account: Account, topic: Topic): UserTopicRelation?
}
