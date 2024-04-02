package net.ezlotest.domain.usecases

import net.ezlotest.domain.entities.Profile
import javax.inject.Inject

class GetFakeProfileUseCase @Inject constructor() {

    fun invoke(): Profile =
        Profile("John Wayne", "https://gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50")
}