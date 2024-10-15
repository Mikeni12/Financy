package mx.mikeni.data.users

interface IUsersRemoteDataSource {
    suspend fun getUser(userId: String): Result<User>
}
