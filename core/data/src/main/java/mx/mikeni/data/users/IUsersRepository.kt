package mx.mikeni.data.users

interface IUsersRepository {
    suspend fun getUser(userId: String): Result<User>
}
