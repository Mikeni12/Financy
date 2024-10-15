package mx.mikeni.data.users

internal class UsersRepository(private val usersRemoteDataSource: IUsersRemoteDataSource) : IUsersRepository {

    override suspend fun getUser(userId: String) = usersRemoteDataSource.getUser(userId)
}
