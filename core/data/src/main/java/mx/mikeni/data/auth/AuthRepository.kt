package mx.mikeni.data.auth

internal class AuthRepository(private val authRemoteDataSource: IAuthRemoteDataSource) : IAuthRepository {

    override suspend fun signIn(email: String, password: String) = authRemoteDataSource.signIn(email, password)

    override suspend fun signUp(email: String, password: String) = authRemoteDataSource.signUp(email, password)
}
