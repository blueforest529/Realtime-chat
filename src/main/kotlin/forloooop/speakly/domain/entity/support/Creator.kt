package forloooop.speakly.domain.entity.support

import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.entity.LinkedAuth
import forloooop.speakly.domain.entity.Tag
import forloooop.speakly.domain.enums.AuthType
import forloooop.speakly.domain.interfaces.Entityable

class Creator {

    data class AccountCreator(
        val email: String,
        val nickname: String,
        val linkedAuthCreator: LinkedAuthCreator
    ) : Entityable<Account> {

        override fun toEntity(): Account {
            return Account(
                email = email,
                nickname = nickname
            ).addLinkedAuth(
                linkedAuthCreator.toEntity()
            )
        }
    }

    data class LinkedAuthCreator(
        val type: AuthType,
        val providerUserId: String,
    ) : Entityable<LinkedAuth> {

        override fun toEntity(): LinkedAuth {
            return LinkedAuth(
                type = type,
                providerUserId = providerUserId,
            )
        }
    }

}
