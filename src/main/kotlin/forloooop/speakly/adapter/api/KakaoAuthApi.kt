package forloooop.speakly.adapter.api

import forloooop.speakly.adapter.api.data.response.KakaoUserInfoResponse
import forloooop.speakly.domain.entity.support.Auth
import org.springframework.stereotype.Component
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONObject

@Component
class KakaoAuthApi {

    companion object {
        const val TOKEN_URL = "https://kauth.kakao.com/oauth/token"
        const val USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"
    }

    // Authorization Code를 이용하여 Access Token을 요청하는 메서드
    fun requestAccessToken(authCode: String, clientId: String, clientSecret: String, redirectUri: String): Auth {
        val url = URL(TOKEN_URL)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true

        val params = "grant_type=authorization_code&client_id=$clientId&client_secret=$clientSecret&code=$authCode&redirect_uri=$redirectUri"
        connection.outputStream.write(params.toByteArray())

        if (connection.responseCode == 200) {
            val response = connection.inputStream.bufferedReader().use { it.readText() }
            val jsonResponse = JSONObject(response)

            // JSON에서 access_token 및 refresh_token 추출
            val accessToken = jsonResponse.getString("access_token")
            val refreshToken = jsonResponse.optString("refresh_token", "")

            return Auth(accessToken = accessToken, refreshToken = refreshToken)
        } else {
            throw Exception("토큰 발급 실패")
        }
    }

    // Access Token을 이용하여 카카오 사용자 정보를 가져오는 메서드
    fun getUserInfo(accessToken: String): KakaoUserInfoResponse {
        val url = URL(USER_INFO_URL)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Authorization", "Bearer $accessToken")

        if (connection.responseCode == 200) {
            val reader = connection.inputStream.bufferedReader()
            val response = reader.readText()
            reader.close()

            // JSON 응답 파싱
            val jsonResponse = JSONObject(response)
            val id = jsonResponse.getLong("id")

            // 이메일 정보 추출
            val kakaoAccount = jsonResponse.getJSONObject("kakao_account")
            val email = if (kakaoAccount.has("email")) kakaoAccount.getString("email") else null

            // 닉네임 정보 추출
            val properties = jsonResponse.getJSONObject("properties")
            val nickname = if (properties.has("nickname")) properties.getString("nickname") else null

            return KakaoUserInfoResponse(id = id, email = email, nickname = nickname)
        } else {
            val errorStream = connection.errorStream.bufferedReader().readText()
            throw Exception("사용자 정보 가져오기 실패. Response Code: ${connection.responseCode}, Error: $errorStream")
        }
    }
}
