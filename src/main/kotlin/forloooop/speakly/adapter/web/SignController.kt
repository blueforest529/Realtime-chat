package forloooop.speakly.adapter.web

import forloooop.speakly.adapter.web.data.request.SignApiRequestGroup
import forloooop.speakly.adapter.web.data.response.SignApiResponseGroup
import forloooop.speakly.application.port.input.AuthReadCase
import forloooop.speakly.application.port.input.SignUseCase
import forloooop.speakly.domain.entity.Account
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class SignController(
    private var signUseCase: SignUseCase<Account>,
    private var authReadCase: AuthReadCase
) {

    @PostMapping("/sign-in")
    fun signInByEmail(@RequestBody request: SignApiRequestGroup.SignUpApiRequest): ResponseEntity<SignApiResponseGroup.SignUpApiResponse> {
        val creator = request.toCreator()

        val account = signUseCase.signIn(creator)

        return ok(SignApiResponseGroup.SignUpApiResponse(account))
    }

    @PostMapping("/sign-in/check-duplicate-id")
    fun checkDuplicateByClientId(@RequestBody request: SignApiRequestGroup.DuplicateIdCheckApiRequest): ResponseEntity<Boolean> {
        return ResponseEntity<Boolean>(authReadCase.hasDuplicated(request.authType, request.clientId), HttpStatus.OK)
    }
}
