package forloooop.speakly.adapter.persistance.jpa

import forloooop.speakly.domain.entity.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository : JpaRepository<ChatMessage, Long>