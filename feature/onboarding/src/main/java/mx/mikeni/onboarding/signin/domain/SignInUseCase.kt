package mx.mikeni.onboarding.signin.domain

import mx.mikeni.onboarding.signin.data.IAuthRepository

class SignInUseCase(private val authRepository: IAuthRepository) : ISignInUseCase {

    override suspend fun signIn(email: String, password: String): Result<String> {
        return authRepository.signIn(email, password)
    }
}
