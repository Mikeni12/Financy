package mx.mikeni.onboarding.signin.domain

import mx.mikeni.data.auth.IAuthRepository

class SignInUseCase(private val authRepository: IAuthRepository) : ISignInUseCase {

    override suspend fun signIn(email: String, password: String): Result<String> {
        return authRepository.signIn(email, password)
    }
}
