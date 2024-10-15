package mx.mikeni.home.domain

import mx.mikeni.data.users.IUsersRepository
import mx.mikeni.data.users.User

class GetUserUseCase(private val userRepository: IUsersRepository) : IGetUserUseCase {
    override suspend fun getUser(userId: String): Result<User> {
        return userRepository.getUser(userId)
    }
}
