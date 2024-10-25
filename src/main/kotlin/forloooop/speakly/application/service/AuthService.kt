package forloooop.speakly.application.service

import forloooop.speakly.adapter.api.KakaoAuthApi
import forloooop.speakly.application.port.input.AuthReadCase
import forloooop.speakly.application.port.output.AccountReadOutput
import forloooop.speakly.application.port.output.SignOutput
import forloooop.speakly.application.security.JwtProvider
import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.entity.support.Creator
import forloooop.speakly.domain.enums.AuthType
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val kakaoAuthApi: KakaoAuthApi,
    private val accountReadOutput: AccountReadOutput,
    private val signOutput: SignOutput<Account>,
    private val jwtProvider: JwtProvider
): AuthReadCase {

    // Authorization Code로 Access Token 요청
    fun kakaoLoginAndUserInfo(code: String, redirectUri: String, clientId: String, clientSecret: String): String? {
        // Step 1: Authorization Code로 Access Token 요청
        val auth = kakaoAuthApi.requestAccessToken(code, clientId, clientSecret, redirectUri)

        // Step 2: Access Token으로 사용자 정보(닉네임) 가져오기
        val kakaoUserInfo  = kakaoAuthApi.getUserInfo(auth.accessToken)
        val kakaoUserId = kakaoUserInfo.id.toString()
        val kakaoEmail = kakaoUserInfo.email ?: "noemail@kakao.com"
        val kakaoNickname = kakaoUserInfo.nickname ?: "카카오사용자"

//        // 사용자 정보 반환 (닉네임)
//        return userInfo

        // 사용자 존재 여부 확인 및 처리
        val exists = hasDuplicated(AuthType.KAKAO, kakaoUserId)
        val account = if (exists) {
            accountReadOutput.findByLinkedAuth(AuthType.KAKAO, kakaoUserId)
        } else {
            signOutput.saveAccount(Creator.AccountCreator(
                email = kakaoEmail,
                nickname = kakaoNickname,
                linkedAuthCreator = Creator.LinkedAuthCreator(
                    type = AuthType.KAKAO,
                    providerUserId = kakaoUserId
                )
            ))
        }

        // JWT 토큰 생성
        val jwtToken = account?.let { jwtProvider.generateToken(it) }

        // 토큰 반환
        return jwtToken
    }

    override fun hasDuplicated(authType: AuthType, providerUserId: String): Boolean {
        return accountReadOutput.existsByLinkedAuth(authType, providerUserId)
    }
}
