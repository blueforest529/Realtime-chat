package forloooop.speakly.adapter.web.data.request

import forloooop.speakly.domain.entity.support.Creator
import forloooop.speakly.domain.enums.AuthType
import java.util.UUID

class SignApiRequestGroup {

    data class SignUpApiRequest(
        val email: String,
        val authType: AuthType,
        val providerUserId: String,
        val password: String,
    ) {
        fun toCreator() = Creator.AccountCreator (
            email = email,
            nickname = UUID.randomUUID().toString(),
            linkedAuthCreator = Creator.LinkedAuthCreator(
                type = authType,
                providerUserId = providerUserId
            )
        )
    }

    data class DuplicateIdCheckApiRequest(
        val authType: AuthType,
        val clientId: String
    )
}
