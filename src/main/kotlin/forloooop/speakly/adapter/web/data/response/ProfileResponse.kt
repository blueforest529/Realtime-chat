package forloooop.speakly.adapter.web.data.response

import forloooop.speakly.domain.entity.Account
import org.springframework.context.annotation.Profile

class ProfileResponse(
    val id: Long?,
    val nickname: String,
) {
    companion object {
        fun from(account: Account): ProfileResponse {
            return ProfileResponse(
                id = account.id,
                nickname = account.nickname,
            )

        }
    }
}
