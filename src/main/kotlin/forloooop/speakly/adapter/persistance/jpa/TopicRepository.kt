package forloooop.speakly.adapter.persistance.jpa

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import forloooop.speakly.domain.entity.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicRepository : JpaRepository<Topic, Long>, KotlinJdslJpqlExecutor {
    fun findAllByOrderByNumberOfAttendeeDesc(): List<Topic>
}
