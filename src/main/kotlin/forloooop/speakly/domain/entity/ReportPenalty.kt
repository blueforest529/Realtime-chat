package forloooop.speakly.domain.entity

import forloooop.speakly.domain.enums.ReportStatus
import forloooop.speakly.domain.enums.ReportType
import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "report_penalty")
class ReportPenalty {

    @Id
    @Tsid
    var id: Long? = null

    @Column(nullable = false)
    var cause: String = ""

    @Column(nullable = false)
    var penaltyScore: Int = 0

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: ReportStatus = ReportStatus.PENDING

    @Column(nullable = true)
    var completedAt: LocalDateTime? = null

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: ReportType = ReportType.ETC

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    lateinit var reporter: Account

    @ManyToOne
    @JoinColumn(name = "target_account_id")
    lateinit var targetAccount: Account

    @ManyToOne
    @JoinColumn(name = "message_id")
    lateinit var message: ChatMessage
}
