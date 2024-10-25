package forloooop.speakly.application.port.input

import forloooop.speakly.domain.entity.Topic

interface TopicUseCase {
    fun save(topic: List<Topic>)
}