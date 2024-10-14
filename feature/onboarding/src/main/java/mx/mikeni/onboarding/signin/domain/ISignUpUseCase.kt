package mx.mikeni.onboarding.signin.domain

interface ISignUpUseCase {
    suspend fun signUp(email: String, password: String): Result<String>
}
