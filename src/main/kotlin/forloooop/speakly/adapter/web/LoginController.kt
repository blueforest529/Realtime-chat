package forloooop.speakly.adapter.web

import forloooop.speakly.application.service.AuthService
import forloooop.speakly.config.KakaoOAuthConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

// 카카오 로그인시 웹페이지 실행 필요
@Controller
@RequestMapping("/")
class LoginController(
    private val KakaoOAuthConfig: KakaoOAuthConfig
) {

    @GetMapping("/login")
    fun login(): String {
        // 카카오 로그인 페이지로 리디렉션
        val kakaoLoginUrl = "https://kauth.kakao.com/oauth/authorize" +
                "?client_id=${KakaoOAuthConfig.clientId}" +
                "&redirect_uri=${KakaoOAuthConfig.redirectUri}" +
                "&response_type=code"

        return "redirect:$kakaoLoginUrl"
    }
}
