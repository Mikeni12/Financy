package mx.mikeni.onboarding.signup.domain

import android.net.Uri
import mx.mikeni.data.auth.AuthRequest
import mx.mikeni.data.auth.IAuthRepository
import mx.mikeni.data.users.IUsersRepository

class SignUpUseCase(private val authRepository: IAuthRepository,
                    private val usersRepository: IUsersRepository) : ISignUpUseCase {

    override suspend fun signUp(email: String, password: String, name: String, lastName: String, photoId: Uri): Result<String> {
        val photoResult = usersRepository.setPhotoId(photoId)
        if (photoResult.isFailure) return photoResult
        val userResult = authRepository.signUp(email, password)
        if (userResult.isFailure) return userResult
        val userId = userResult.getOrElse { return Result.failure(it) }
        val photoUrl = photoResult.getOrElse { return Result.failure(it) }
        val result = usersRepository.setUser(userId, AuthRequest(email, password, name, lastName, photoUrl))
        if (result.isFailure) return Result.failure(result.exceptionOrNull() ?: Exception())
        return Result.success(userId)
    }
}
