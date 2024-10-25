package forloooop.speakly.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.kakao")
class KakaoOAuthConfig {
    lateinit var clientId: String
    lateinit var clientSecret: String
    lateinit var redirectUri: String
}
