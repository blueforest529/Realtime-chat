package forloooop.speakly.adapter.persistance.jpa

import forloooop.speakly.domain.entity.ChatMessageRedis
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatMessageRedisRepository : JpaRepository<ChatMessageRedis, Long>
