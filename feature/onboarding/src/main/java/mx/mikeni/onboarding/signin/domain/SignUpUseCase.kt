package mx.mikeni.onboarding.signin.domain

import mx.mikeni.onboarding.signin.data.IAuthRepository

class SignUpUseCase(private val authRepository: IAuthRepository) : ISignUpUseCase {

    override suspend fun signUp(email: String, password: String): Result<String> {
        return authRepository.signUp(email, password)
    }
}
