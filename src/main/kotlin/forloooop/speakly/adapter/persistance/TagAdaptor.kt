package forloooop.speakly.adapter.persistance

import forloooop.speakly.adapter.persistance.jpa.TagRepository
import forloooop.speakly.application.port.output.TagOutput
import forloooop.speakly.domain.entity.Tag
import org.springframework.stereotype.Service

@Service
class TagAdaptor(
    private  val tagRepository: TagRepository
): TagOutput {

    override fun getAll(): List<Tag> {
        return tagRepository.findAll()
    }

    override fun register(tagNames: List<String>): List<Tag> {
        return tagNames.map { tagName ->
            tagRepository.findByName(tagName) ?: tagRepository.save(Tag(name = tagName))
        }
    }
}