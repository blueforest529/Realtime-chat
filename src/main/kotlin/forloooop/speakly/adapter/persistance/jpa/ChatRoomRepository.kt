package forloooop.speakly.adapter.persistance.jpa

import forloooop.speakly.domain.entity.ChatMessage
import forloooop.speakly.domain.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRoomRepository : JpaRepository<ChatRoom, Long>