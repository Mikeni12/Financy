package mx.mikeni.onboarding.signup.domain

import android.net.Uri

interface ISignUpUseCase {
    suspend fun signUp(email: String, password: String, name: String, lastName: String, photoId: Uri): Result<String>
}
