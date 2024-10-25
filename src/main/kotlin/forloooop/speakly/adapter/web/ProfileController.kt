package forloooop.speakly.adapter.web

import forloooop.speakly.adapter.web.data.request.ModifyProfileRequest
import forloooop.speakly.adapter.web.data.response.ProfileResponse
import forloooop.speakly.application.port.input.ProfileUseCase
import forloooop.speakly.domain.entity.Account
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/profile")
class ProfileController(
    private var profileUseCase: ProfileUseCase
) {
    @PutMapping("/modify")
    fun modifyProfile(@RequestBody request: ModifyProfileRequest): ResponseEntity<ProfileResponse> {
        val (id, nickname) = request
        val account = profileUseCase.modifyProfile(id, nickname)
        return ResponseEntity.ok(ProfileResponse.from(account))
    }
}