package forloooop.speakly.adapter.persistance

import forloooop.speakly.adapter.persistance.jpa.UserTopicRelationRepository
import forloooop.speakly.application.port.output.UserTopicRelationOutput
import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.entity.Topic
import forloooop.speakly.domain.entity.relation.UserTopicRelation
import org.springframework.stereotype.Service

@Service
class UserTopicRelationAdaptor(
    private val userTopicRelationRepository: UserTopicRelationRepository
) : UserTopicRelationOutput {

    override fun save(userTopicRelation: UserTopicRelation) {
        userTopicRelationRepository.save(userTopicRelation)
    }

    override fun delete(userTopicRelation: UserTopicRelation) {
        userTopicRelationRepository.delete(userTopicRelation)
    }

    override fun existsByAccountAndTopic(account: Account, topic: Topic): Boolean {
        return userTopicRelationRepository.existsByAccountAndTopic(account, topic)
    }

    override fun findByAccountAndTopic(account: Account, topic: Topic): UserTopicRelation? {
        return userTopicRelationRepository.findByAccountAndTopic(account, topic)
    }
}
