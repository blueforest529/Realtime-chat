package forloooop.speakly.application.service

import com.fasterxml.jackson.databind.ObjectMapper
import forloooop.speakly.adapter.persistance.jpa.ChatMessageRedisRepository
import forloooop.speakly.domain.entity.ChatMessageRedis
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.transaction.annotation.Transactional

@Service
class RedisListenerService(
    private val chatMessageRedisRepository: ChatMessageRedisRepository,
    private val objectMapper: ObjectMapper
) : MessageListener {

    private val messageBuffer = mutableListOf<ChatMessageRedis>() // 메시지 임시 저장 리스트
    private val logger = LoggerFactory.getLogger(RedisListenerService::class.java)

    override fun onMessage(message: Message, pattern: ByteArray?) {
        try {
            val chatMessage = objectMapper.readValue(message.body, ChatMessageRedis::class.java)
            messageBuffer.add(chatMessage)
            logger.info("Message received: $chatMessage")
            logger.info("Current message buffer size: ${messageBuffer.size}")

            // 메시지 개수가 5개 이상인 경우 DB에 저장
            if (messageBuffer.size >= 5) {
                saveMessagesToDatabase()
            }
        } catch (e: Exception) {
            println("Error processing Redis message: ${e.message}")
        }
    }

    @Transactional
    fun saveMessagesToDatabase() {
        chatMessageRedisRepository.saveAll(messageBuffer) // DB에 저장
        messageBuffer.clear()
    }
}