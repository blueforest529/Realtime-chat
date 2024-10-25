package forloooop.speakly.adapter.web.data.response

import forloooop.speakly.domain.entity.Account

class SignApiResponseGroup {

    data class SignUpApiResponse(
        val id: Long,
        val email: String,
        val nickname: String,
    ) {
        constructor(account: Account) : this(
            id = account.id!!,
            email = account.email,
            nickname = account.nickname
        )
    }
}
