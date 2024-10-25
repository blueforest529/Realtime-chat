package forloooop.speakly.domain.entity

import forloooop.speakly.domain.enums.AuthType
import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.*

@Entity
@Table(name = "linked_auth")
class LinkedAuth(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: AuthType = AuthType.DEFAULT,

    @Column(name = "provider_user_id", nullable = false)
    val providerUserId: String = ""
) {

    @Id
    @Tsid
    var id: Long? = null

    @Column(nullable = false)
    var password: String = ""

    @ManyToOne
    @JoinColumn(name = "account_id")
    lateinit var account: Account

    constructor(type: AuthType, providerUserId: String, password: String) : this(type, providerUserId) {
        this.password = password
    }
}
