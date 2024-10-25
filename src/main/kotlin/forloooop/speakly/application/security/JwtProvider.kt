package forloooop.speakly.application.security

import forloooop.speakly.domain.entity.Account
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

/**
 * JWT 토큰의 생성 및 검증을 담당하는 컴포넌트입니다.
 */
@Component
class JwtProvider(
    @Value("\${jwt.secret}") private val secretKey: String,
    @Value("\${jwt.expiration}") private val expirationTime: Long
) {
    /**
     * 주어진 계정 정보를 바탕으로 JWT 토큰을 생성합니다.
     *
     * @param account JWT 토큰에 포함시킬 계정 정보 객체
     * @return 생성된 JWT 토큰 문자열
     */
    fun generateToken(account: Account): String {
        // JWT 클레임 설정 (주제, 이메일, 닉네임)
        val claims = Jwts.claims().setSubject(account.id.toString())
        claims["email"] = account.email
        claims["nickname"] = account.nickname

        // JWT 빌더를 사용하여 토큰 생성
        return Jwts.builder()
            .setClaims(claims) // 클레임 설정
            .setIssuedAt(Date()) // 발행 시간 설정
            .setExpiration(Date(System.currentTimeMillis() + expirationTime)) // 만료 시간 설정
            .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray()), SignatureAlgorithm.HS256) // 서명 설정
            .compact() // 토큰 문자열로 압축
    }

    /**
     * 주어진 JWT 토큰의 유효성을 검증합니다.
     *
     * @param token 검증할 JWT 토큰 문자열
     * @return 유효한 토큰이면 true, 그렇지 않으면 false
     */
    fun validateToken(token: String): Boolean {
        return try {
            // 토큰 파싱 및 클레임 추출
            val claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.toByteArray())
                .build()
                .parseClaimsJws(token)

            // 토큰의 만료 여부 확인
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            // 토큰이 유효하지 않거나 파싱 중 예외 발생 시 false 반환
            false
        }
    }

    /**
     * JWT 토큰에서 계정 ID를 추출합니다.
     *
     * @param token 추출할 JWT 토큰 문자열
     * @return 추출된 계정 ID (Long 타입)
     */
    fun getAccountIdFromToken(token: String): Long {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey.toByteArray())
            .build()
            .parseClaimsJws(token)
        return claims.body.subject.toLong()
    }

    /**
     * JWT 토큰에서 이메일을 추출합니다.
     *
     * @param token 추출할 JWT 토큰 문자열
     * @return 추출된 이메일 문자열
     */
    fun getEmailFromToken(token: String): String {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey.toByteArray())
            .build()
            .parseClaimsJws(token)
        return claims.body["email"].toString()
    }

    /**
     * JWT 토큰에서 닉네임을 추출합니다.
     *
     * @param token 추출할 JWT 토큰 문자열
     * @return 추출된 닉네임 문자열
     */
    fun getNicknameFromToken(token: String): String {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey.toByteArray())
            .build()
            .parseClaimsJws(token)
        return claims.body["nickname"].toString()
    }
}
