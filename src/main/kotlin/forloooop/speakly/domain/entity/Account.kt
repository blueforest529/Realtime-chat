package forloooop.speakly.domain.entity

import forloooop.speakly.domain.entity.relation.UserTopicRelation
import forloooop.speakly.domain.enums.AccountStatus
import forloooop.speakly.domain.enums.Role
import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "account")
class Account() {

    @Id
    @Tsid
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var email: String = ""

    @Column(nullable = false)
    var nickname: String = ""

    @Column(nullable = true)
    var profileImagePath: String = ""

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var authority: Role = Role.USER

    @Column(nullable = false)
    var createAt: LocalDateTime = LocalDateTime.now()

    @Column(nullable = true)
    var lastLoggedIn: LocalDateTime? = null

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: AccountStatus = AccountStatus.SIGNUP

    @Column(nullable = false)
    var emailAlert: Boolean = false

    @Column(nullable = false)
    var pushAlert: Boolean = false

    @Column(nullable = false)
    var penalty: Int = 0

    @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL], orphanRemoval = true)
    var linkedAuthGroup: List<LinkedAuth> = mutableListOf()

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var topicGroup: List<Topic> = mutableListOf()

    @OneToMany(mappedBy = "reporter", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var reportedPenaltyGroup: List<ReportPenalty> = mutableListOf()

    @OneToMany(mappedBy = "targetAccount", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var receivedPenaltyGroup: List<ReportPenalty> = mutableListOf()

    @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var userTopicRelationGroup : List<UserTopicRelation> = mutableListOf()

    constructor(email: String, nickname: String) : this() {
        this.email = email
        this.nickname = nickname
    }

    fun addLinkedAuth(linkedAuth: LinkedAuth): Account {
        linkedAuth.account = this
        linkedAuthGroup += linkedAuth
        return this
    }

    fun modifyProfile(nickname: String) {
        this.nickname = nickname
    }
}
