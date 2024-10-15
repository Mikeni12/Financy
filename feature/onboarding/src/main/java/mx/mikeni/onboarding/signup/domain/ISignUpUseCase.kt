package mx.mikeni.onboarding.signup.domain

interface ISignUpUseCase {
    suspend fun signUp(email: String, password: String): Result<String>
}
