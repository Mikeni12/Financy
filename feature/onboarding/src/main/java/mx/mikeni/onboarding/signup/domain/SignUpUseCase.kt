package mx.mikeni.onboarding.signup.domain

import mx.mikeni.data.auth.IAuthRepository

class SignUpUseCase(private val authRepository: IAuthRepository) : ISignUpUseCase {

    override suspend fun signUp(email: String, password: String): Result<String> {
        return authRepository.signUp(email, password)
    }
}
