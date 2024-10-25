package forloooop.speakly.application.service

import forloooop.speakly.application.port.output.TopicOutput
import forloooop.speakly.application.port.output.UserTopicRelationOutput
import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.entity.Topic
import forloooop.speakly.domain.entity.relation.UserTopicRelation
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TopicService(
    private val topicOutput: TopicOutput,
    private val userTopicRelationOutput: UserTopicRelationOutput
) {

    fun findTopicById(topicId: Long): Topic {
        return topicOutput.findById(topicId) ?: throw IllegalArgumentException("$topicId 토픽 없음")
    }

    @Transactional
    fun saveTopic(topic: Topic): Topic {
        return topicOutput.register(topic)
    }

    @Transactional
    fun participateInTopic(account: Account, topic: Topic) {
        if (userTopicRelationOutput.existsByAccountAndTopic(account, topic)) {
            throw IllegalStateException("이미 참여 중")
        }

        val relation = UserTopicRelation(
            account = account,
            topic = topic,
            joinedAt = LocalDateTime.now()
        )
        userTopicRelationOutput.save(relation)

        topic.numberOfAttendee++
        topicOutput.register(topic)
    }

    @Transactional
    fun leaveTopic(account: Account, topic: Topic) {
        val relation = userTopicRelationOutput.findByAccountAndTopic(account, topic)
            ?: throw IllegalStateException("참여 중 아님")

        userTopicRelationOutput.delete(relation)

        if (topic.numberOfAttendee > 0) {
            topic.numberOfAttendee--
        }
        topicOutput.register(topic)
    }
}
