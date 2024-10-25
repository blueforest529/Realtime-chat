package forloooop.speakly.adapter.persistance.jpa

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import forloooop.speakly.domain.entity.ChatMessage
import forloooop.speakly.domain.entity.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ChatMessageRepository : JpaRepository<ChatMessage, Long>, KotlinJdslJpqlExecutor {
    fun countByTopicAndCreatedAtAfter(topic: Topic, fromDate: LocalDateTime): Long
}
