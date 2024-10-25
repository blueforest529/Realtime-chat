package forloooop.speakly.application.port.output

import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.entity.Topic
import forloooop.speakly.domain.entity.relation.UserTopicRelation

interface UserTopicRelationOutput {
    fun save(userTopicRelation: UserTopicRelation)
    fun delete(userTopicRelation: UserTopicRelation)
    fun existsByAccountAndTopic(account: Account, topic: Topic): Boolean
    fun findByAccountAndTopic(account: Account, topic: Topic): UserTopicRelation?
}
