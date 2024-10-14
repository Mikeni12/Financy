package mx.mikeni.onboarding.signin.data

interface IAuthRepository {
    suspend fun signIn(email: String, password: String): Result<String>
    suspend fun signUp(email: String, password: String): Result<String>
}
