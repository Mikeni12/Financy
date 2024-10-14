package mx.mikeni.onboarding.signin.data

import mx.mikeni.data.auth.IAuthRemoteDataSource

class AuthRepository(private val authRemoteDataSource: IAuthRemoteDataSource) : IAuthRepository {

    override suspend fun signIn(email: String, password: String) = authRemoteDataSource.signIn(email, password)

    override suspend fun signUp(email: String, password: String) = authRemoteDataSource.signUp(email, password)
}
