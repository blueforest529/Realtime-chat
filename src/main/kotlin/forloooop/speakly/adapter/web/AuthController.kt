package forloooop.speakly.adapter.web

import forloooop.speakly.application.service.AuthService
import forloooop.speakly.config.KakaoOAuthConfig
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URLEncoder


@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val KakaoOAuthConfig: KakaoOAuthConfig,

    @Value("\${frontend.url}")
    private val frontendUrl: String
) {

    @GetMapping("/kakao/callback")
    fun kakaoLoginCallback(@RequestParam code: String, response: HttpServletResponse) {

        // 카카오 인증 후 넘어온 code 값으로 로그인 처리 진행
        try {
            val jwtToken = authService.kakaoLoginAndUserInfo(
                code = code,  // 전달된 Authorization Code 사용
                redirectUri = KakaoOAuthConfig.redirectUri,
                clientId = KakaoOAuthConfig.clientId,
                clientSecret = KakaoOAuthConfig.clientSecret
            )

//            // 세션에 사용자 닉네임 저장
//            session.setAttribute("user", nickname)
//            println("카카오 로그인 성공, 사용자 닉네임: $nickname")

            // 프론트로 리디렉션하면서 토큰 전달
            val encodedToken = URLEncoder.encode(jwtToken, "UTF-8")
            val redirectUrl = "$frontendUrl/auth/auth2/callback?token=$encodedToken"
            response.sendRedirect(redirectUrl)

        } catch (e: Exception) {
            println("카카오 로그인 실패: ${e.message}")
        }
    }

}
