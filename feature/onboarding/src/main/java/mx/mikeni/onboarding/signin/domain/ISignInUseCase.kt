package mx.mikeni.onboarding.signin.domain

interface ISignInUseCase {
    suspend fun signIn(email: String, password: String): Result<String>
}
