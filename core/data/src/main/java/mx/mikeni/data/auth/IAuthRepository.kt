package mx.mikeni.data.auth

interface IAuthRepository {
    suspend fun signIn(email: String, password: String): Result<String>
    suspend fun signUp(email: String, password: String): Result<String>
}
